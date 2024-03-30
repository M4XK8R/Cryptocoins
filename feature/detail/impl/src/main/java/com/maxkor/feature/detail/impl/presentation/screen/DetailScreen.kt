package com.maxkor.feature.detail.impl.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.maxkor.core.theme.LocalFontScaling
import com.maxkor.core.theme.LocalSpacing
import com.maxkor.core.ui.components.TitleText
import com.maxkor.core.ui.images.CryptocoinsImages
import com.maxkor.core.ui.preview.PreviewProvider
import com.maxkor.core.ui.preview.annotations.RawPreview
import com.maxkor.feature.detail.impl.domain.model.DetailCoin
import com.maxkor.feature.detail.impl.presentation.mapper.toDetailCoinVo
import com.maxkor.feature.detail.impl.presentation.model.DetailCoinVo

private const val HEADER_ROW_SIZE = 200
private const val MAIN_IMAGE_SIZE = 180

@Composable
internal fun DetailScreen(
    detailCoinVo: DetailCoinVo,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current
    val fontScaling = LocalFontScaling.current

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)
            .padding(spacing.spaceMedium)
            .then(modifier),
        verticalArrangement = Arrangement.Top
    ) {
        Spacer(modifier = Modifier.size(spacing.spaceSmall))
        Row(
            modifier = Modifier
//                .background(Color.DarkGray)
                .fillMaxWidth()
                .height(HEADER_ROW_SIZE.dp)
                .padding(spacing.spaceSmall),
            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                model = detailCoinVo.imageUrl,
                contentDescription = "Cryptocoin picture",
                modifier = Modifier.size(MAIN_IMAGE_SIZE.dp)
            )
            Spacer(modifier = Modifier.size(spacing.spaceExtraLarge))
            Column(
                modifier = Modifier.fillMaxHeight(),
                verticalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    CoinNameText(
                        text = detailCoinVo.name,
                        fontScaling = fontScaling.increasingExtraLarge
                    )
                    Spacer(modifier = Modifier.size(spacing.spaceExtraSmall))
                    TitleText(text = detailCoinVo.price)
                }
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
//                        .background(Color.Gray)
                    ,
                    horizontalArrangement = Arrangement.End
                ) {
                    Image(
                        painter = painterResource(id = CryptocoinsImages.Download),
                        contentDescription = "Download image"
                    )
                    Spacer(modifier = Modifier.size(spacing.spaceExtraSmall))
                    Image(
                        painter = painterResource(id = CryptocoinsImages.Share),
                        contentDescription = "Share image"
                    )
                    Spacer(modifier = Modifier.size(spacing.spaceExtraSmall))
                    Image(
                        painter = painterResource(id = CryptocoinsImages.Notify),
                        contentDescription = "Notify image"
                    )
                }

            }
        }
        Spacer(modifier = Modifier.size(spacing.spaceMedium))
        CoinExtraInfoText(
            text = detailCoinVo.extraInfo,
            fontScaling = fontScaling.increasingLarge
        )
    }
}

/**
 * Private functions
 */

@Composable
fun CoinNameText(
    text: String,
    fontScaling: TextUnit,
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = fontScaling,
        style = MaterialTheme.typography.titleLarge,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily.Serif,
    )
}

@Composable
fun CoinExtraInfoText(
    text: String,
    fontScaling: TextUnit,
) {
    Text(
        text = text,
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = fontScaling,
        style = MaterialTheme.typography.headlineMedium,
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily.Default,
        textAlign = TextAlign.Justify,
        maxLines = 14,
        softWrap = true,
        overflow = TextOverflow.Ellipsis
    )
}

/**
 * Preview
 */
@Composable
@RawPreview
fun RunPreviewDetailScreen() {
    PreviewProvider(
        content = {
            DetailScreen(
                detailCoinVo = DetailCoin.testExemplar.toDetailCoinVo()
            )
        }
    ).DeviceRunnable()
}