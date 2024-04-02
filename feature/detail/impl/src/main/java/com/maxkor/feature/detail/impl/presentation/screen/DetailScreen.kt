package com.maxkor.feature.detail.impl.presentation.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.maxkor.core.theme.LocalFontScaling
import com.maxkor.core.theme.LocalSpacing
import com.maxkor.core.ui.components.ButtonText
import com.maxkor.core.ui.components.TitleText
import com.maxkor.core.ui.icons.CryptocoinsIcons
import com.maxkor.core.ui.images.CryptocoinsImages
import com.maxkor.core.ui.preview.PreviewProvider
import com.maxkor.core.ui.preview.annotations.RawPreview
import com.maxkor.feature.detail.impl.domain.model.DetailCoin
import com.maxkor.feature.detail.impl.presentation.mapper.toDetailCoinVo
import com.maxkor.feature.detail.impl.presentation.model.DetailCoinVo

private const val MAIN_IMAGE_SIZE = 180

@Composable
internal fun DetailScreen(
    detailCoinVo: DetailCoinVo,
    detailUiState: DetailUiState,
    savePicture: (url: String, name: String) -> Unit,
    sharePicture: (url: String) -> Unit,
    createReminder: () -> Unit,
    addCoinExtraInfo: () -> Unit,
    saveCoinExtraInfo: (key: String, extraInfo: String) -> Unit,
    onTextSectionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val spacing = LocalSpacing.current
    val fontScaling = LocalFontScaling.current

    var coinExtraInfoInput by remember {
        mutableStateOf(detailCoinVo.extraInfo)
    }
    val editCoinExtraInfoInput: (String) -> Unit = { inputText ->
        coinExtraInfoInput = inputText
    }

    ConstraintLayout(
        modifier = Modifier
            .fillMaxSize()
            .padding(spacing.spaceSmall)
            .then(modifier)
    ) {
        val (
            imageMainRef,
            columnCoinData,
            rowActionIcons,
            boxCoinExtraText,
        ) = createRefs()

        AsyncImage(
            model = detailCoinVo.imageUrl,
            contentDescription = "Cryptocoin picture",
            modifier = Modifier
                .constrainAs(imageMainRef) {
                    start.linkTo(parent.start)
                    top.linkTo(parent.top)
                }
                .size(MAIN_IMAGE_SIZE.dp)
        )
        Column(
            modifier = Modifier.constrainAs(columnCoinData) {
                start.linkTo(imageMainRef.end, spacing.spaceMedium)
                top.linkTo(parent.top)
            }
        ) {
            CoinNameText(
                text = detailCoinVo.name,
                fontScaling = fontScaling.increasingExtraLarge
            )
            Spacer(modifier = Modifier.size(spacing.spaceExtraSmall))
            TitleText(text = detailCoinVo.price)
        }
        Row(
            modifier = Modifier.constrainAs(rowActionIcons) {
                bottom.linkTo(imageMainRef.bottom)
                end.linkTo(parent.end)
            }
        ) {
            ActionImage(
                imageResId = CryptocoinsImages.Download,
                onClick = {
                    savePicture(
                        detailCoinVo.imageUrl,
                        detailCoinVo.name
                    )
                }
            )
            Spacer(modifier = Modifier.size(spacing.spaceExtraSmall))
            ActionImage(
                imageResId = CryptocoinsImages.Share,
                onClick = { sharePicture(detailCoinVo.imageUrl) }
            )
            Spacer(modifier = Modifier.size(spacing.spaceExtraSmall))
            ActionImage(
                imageResId = CryptocoinsImages.Notify,
                onClick = { createReminder() }
            )
        }
        Box(
            modifier = Modifier
                .constrainAs(boxCoinExtraText) {
                    start.linkTo(parent.start)
                    top.linkTo(imageMainRef.bottom, spacing.spaceLarge)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                    width = Dimension.matchParent
                    height = Dimension.fillToConstraints
                }
                .clickable { onTextSectionClick() }
                .shadow(
                    elevation = spacing.spaceUltraSmall,
                    shape = RoundedCornerShape(spacing.spaceUltraSmall)
                )
        ) {
            when (detailUiState) {
                DetailUiState.ModeEdit -> {
                    CoinExtraInfoEditText(
                        value = coinExtraInfoInput,
                        onValueChange = { editCoinExtraInfoInput(it) },
                        editTextFontScaling = fontScaling.increasingSmall,
                        hintFontScaling = fontScaling.increasingSmall,
                        modifier = Modifier.padding(
                            start = spacing.spaceSmall,
                            top = spacing.spaceMedium,
                            end = spacing.spaceSmall,
                            bottom = spacing.spaceMedium
                        )
                    )
                    ExtendedFloatingActionButton(
                        text = { ButtonText(text = "Save") },
                        icon = {
                            Icon(
                                imageVector = CryptocoinsIcons.Done,
                                contentDescription = "Icon"
                            )
                        },
                        onClick = {
                            saveCoinExtraInfo(
                                detailCoinVo.name,
                                coinExtraInfoInput
                            )
                        },
                        modifier = Modifier
                            .align(Alignment.BottomEnd)
                            .padding(
                                end = spacing.spaceMedium,
                                bottom = spacing.spaceMedium
                            )
                    )
                }

                DetailUiState.ModeRead ->
                    CoinExtraInfoText(
                        text = coinExtraInfoInput,
                        fontScaling = fontScaling.increasingSmall,
                        modifier = Modifier
                            .clickable {
                                addCoinExtraInfo()
                            }
                            .padding(spacing.spaceMedium)
                    )
            }
        }
    }
}

/**
 * Private functions
 */

@Composable
private fun ActionImage(
    imageResId: Int,
    onClick: () -> Unit,
) {
    Image(
        painter = painterResource(id = imageResId),
        contentDescription = "Action image",
        modifier = Modifier.clickable { onClick() }
    )
}

@Composable
private fun CoinNameText(
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
private fun CoinExtraInfoText(
    text: String,
    fontScaling: TextUnit,
    modifier: Modifier = Modifier,
) {
    Text(
        text = text.ifEmpty {
            "Click to add some info"
        },
        color = MaterialTheme.colorScheme.onBackground,
        fontSize = fontScaling,
        style = MaterialTheme.typography.bodyMedium,
        fontWeight = FontWeight.Medium,
        fontFamily = FontFamily.Default,
        textAlign = TextAlign.Justify,
        maxLines = 14,
        softWrap = true,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier
    )
}

@Composable
fun CoinExtraInfoEditText(
    value: String,
    onValueChange: (String) -> Unit,
    editTextFontScaling: TextUnit,
    hintFontScaling: TextUnit,
    modifier: Modifier = Modifier,
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier,
        textStyle = LocalTextStyle.current.copy(
            fontSize = editTextFontScaling
        ),
        placeholder = {
            Text(
                text = "Add some info",
                fontSize = hintFontScaling,
                color = MaterialTheme.colorScheme.onBackground,
                style = MaterialTheme.typography.displaySmall,
                fontFamily = FontFamily.Monospace
            )
        },
        maxLines = 14,
        colors = TextFieldDefaults.colors(
            focusedTextColor = MaterialTheme.colorScheme.onBackground,
        )
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
                detailCoinVo = DetailCoin.testExemplar.toDetailCoinVo(),
                detailUiState = DetailUiState.ModeRead,
                savePicture = { _, _ -> },
                sharePicture = {},
                createReminder = {},
                saveCoinExtraInfo = { _, _ -> },
                addCoinExtraInfo = {},
                onTextSectionClick = {}
            )
        }
    ).DeviceRunnable()
}