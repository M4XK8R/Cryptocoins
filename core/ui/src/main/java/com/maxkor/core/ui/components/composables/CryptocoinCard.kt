package com.maxkor.core.ui.components.composables

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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.maxkor.core.base.domain.model.Cryptocoin
import com.maxkor.core.base.presentation.mapper.toCryptocoinVo
import com.maxkor.core.base.presentation.model.CryptocoinVo
import com.maxkor.core.theme.LocalFontScaling
import com.maxkor.core.theme.LocalSpacing
import com.maxkor.core.ui.icons.CryptocoinsIcons
import com.maxkor.core.ui.preview.PreviewProvider
import com.maxkor.core.ui.preview.annotations.RawPreview
import com.maxkor.core.ui.util.CoilImageLoader

private const val DEFAULT_CARD_HEIGHT = 98

@Composable
fun CryptocoinCard(
    cryptocoinVo: CryptocoinVo,
    onCardClick: () -> Unit,
    onFavoriteIconClick: () -> Unit,
    modifier: Modifier = Modifier,
    cardHeight: Int = DEFAULT_CARD_HEIGHT,
) {
    val context = LocalContext.current
    val spacing = LocalSpacing.current
    val fontScaling = LocalFontScaling.current

    Card(
        modifier = Modifier
            .height(cardHeight.dp)
            .fillMaxWidth()
            .padding(spacing.spaceExtraSmall)
            .clickable { onCardClick() }
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
                model = CoilImageLoader.loadSvgImage(
                    context = context,
                    modelUrl = cryptocoinVo.imageUrl
                ),
                contentDescription = stringResource(
                    com.maxkor.core.base.R.string.cryptocoin_picture
                ),
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
                    text = cryptocoinVo.name,
                    fontScaling = fontScaling.increasingMedium
                )
                CoinInfoText(
                    text = cryptocoinVo.price,
                    fontScaling = fontScaling.increasingSmall
                )
            }

            Image(
                imageVector = if (cryptocoinVo.isFavorite) {
                    CryptocoinsIcons.Favorite
                } else {
                    CryptocoinsIcons.NotFavorite
                },
                contentDescription = stringResource(
                    com.maxkor.core.base.R.string.favorite_image
                ),
                modifier = Modifier
                    .constrainAs(imageFavRef) {
                        top.linkTo(parent.top, spacing.spaceSmall)
                        end.linkTo(parent.end, spacing.spaceMedium)
                        bottom.linkTo(parent.bottom, spacing.spaceSmall)
                    }
                    .clickable { onFavoriteIconClick() }
            )
        }
    }
}

/**
 * Private sector
 */
@Composable
private fun CoinInfoText(
    text: String,
    fontScaling: TextUnit,
) = Text(
    text = text,
    fontSize = fontScaling,
    color = MaterialTheme.colorScheme.onSurface,
    style = MaterialTheme.typography.headlineLarge,
    fontFamily = FontFamily.Serif,
    fontWeight = FontWeight.Bold,
)

/**
 * Preview
 */
@Composable
@RawPreview
fun RunPreviewCryptocoinCard() = PreviewProvider(
    content = {
        CryptocoinCard(
            cryptocoinVo = Cryptocoin.testExemplar.toCryptocoinVo(),
            onCardClick = { },
            onFavoriteIconClick = {}
        )
    }
).DeviceRunnable()