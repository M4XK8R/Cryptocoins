package com.maxkor.feature.favorites.impl.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.maxkor.core.theme.LocalFontScaling
import com.maxkor.core.theme.LocalSpacing
import com.maxkor.core.ui.icons.CryptocoinsIcons
import com.maxkor.core.ui.preview.PreviewProvider
import com.maxkor.core.ui.preview.annotations.RawPreview
import com.maxkor.feature.coins.api.domain.model.FavoriteCoin
import com.maxkor.feature.favorites.impl.presentation.mapper.toFavoriteCoinVo
import com.maxkor.feature.favorites.impl.presentation.model.FavoriteCoinVo

private const val CARD_SIZE = 98

@Composable
fun FavoriteCoinCard(
    favoriteCoinVo: FavoriteCoinVo,
    navigateToDetail: (
        name: String,
        price: String,
        imageUrl: String,
    ) -> Unit,
    removeFromFavorites: (FavoriteCoinVo) -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current
    val fontScaling = LocalFontScaling.current

    Card(
        modifier = Modifier
            .height(CARD_SIZE.dp)
            .fillMaxWidth()
            .padding(spacing.spaceExtraSmall)
            .clickable {
                navigateToDetail(
                    favoriteCoinVo.name,
                    favoriteCoinVo.price,
                    favoriteCoinVo.imageUrl
                )
            }
            .then(modifier)
    ) {
        ConstraintLayout(
            modifier = Modifier
                .fillMaxSize()
                .padding(spacing.spaceExtraSmall)
        ) {
            val (
                imageCoinRef,
                columnInfoRef,
                imageFavRef,
            ) = createRefs()

            AsyncImage(
                model = favoriteCoinVo.imageUrl,
                contentDescription = "Avatar image",
                modifier
                    .constrainAs(imageCoinRef) {
                        start.linkTo(parent.start, spacing.spaceMedium)
                        top.linkTo(parent.top, spacing.spaceSmall)
                        bottom.linkTo(parent.bottom, spacing.spaceSmall)
                    }
                    .size(64.dp)
                    .clip(CircleShape),
                contentScale = ContentScale.Crop
            )

            Column(
                modifier = Modifier
                    .constrainAs(columnInfoRef) {
                        height = Dimension.matchParent
                        start.linkTo(imageCoinRef.end, spacing.spaceMedium)
                        top.linkTo(parent.top, spacing.spaceSmall)
                        bottom.linkTo(parent.bottom, spacing.spaceSmall)
                    },
                verticalArrangement = Arrangement.SpaceEvenly,
            ) {
                CoinInfoText(
                    text = favoriteCoinVo.name,
                    fontScaling = fontScaling.increasingMedium
                )
                CoinInfoText(
                    text = favoriteCoinVo.price,
                    fontScaling = fontScaling.increasingSmall
                )
            }

            Image(
                imageVector = CryptocoinsIcons.Favorite,
                contentDescription = "Favorite image",
                modifier = Modifier
                    .constrainAs(imageFavRef) {
                        top.linkTo(parent.top, spacing.spaceSmall)
                        end.linkTo(parent.end, spacing.spaceMedium)
                        bottom.linkTo(parent.bottom, spacing.spaceSmall)
                    }
                    .clickable { removeFromFavorites(favoriteCoinVo) }
            )
        }
    }
}

/**
 * Private functions
 */

@Composable
private fun CoinInfoText(
    text: String,
    fontScaling: TextUnit,
) {
    Text(
        text = text,
        fontSize = fontScaling,
        color = MaterialTheme.colorScheme.onSurface,
        style = MaterialTheme.typography.headlineLarge,
        fontFamily = FontFamily.Serif,
        fontWeight = FontWeight.Bold,
    )
}

/**
 * Preview
 */
@Composable
@RawPreview
fun RunPreviewFavoriteCoinCard() {
    PreviewProvider(
        content = {
            FavoriteCoinCard(
                favoriteCoinVo = FavoriteCoin.testExemplar.toFavoriteCoinVo(),
                navigateToDetail = { _, _, _ -> },
                removeFromFavorites = {}
            )
        }
    ).DeviceRunnable()
}