package com.maxkor.feature.detail.impl.presentation.screen

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.TimePickerState
import androidx.compose.material3.rememberTimePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import coil.compose.AsyncImage
import com.maxkor.core.base.data.images.CryptocoinsImages
import com.maxkor.core.theme.LocalFontScaling
import com.maxkor.core.theme.LocalSpacing
import com.maxkor.core.ui.components.ButtonText
import com.maxkor.core.ui.components.TitleText
import com.maxkor.core.ui.icons.CryptocoinsIcons
import com.maxkor.core.ui.preview.PreviewProvider
import com.maxkor.core.ui.preview.annotations.RawPreview
import com.maxkor.core.ui.util.CoilImageLoader
import com.maxkor.feature.detail.impl.R
import com.maxkor.feature.detail.impl.domain.model.DetailCoin
import com.maxkor.feature.detail.impl.presentation.components.ActionImage
import com.maxkor.feature.detail.impl.presentation.components.CoinExtraInfoEditText
import com.maxkor.feature.detail.impl.presentation.components.CoinExtraInfoText
import com.maxkor.feature.detail.impl.presentation.components.CoinNameText
import com.maxkor.feature.detail.impl.presentation.components.TimePickerSwitchable
import com.maxkor.feature.detail.impl.presentation.mapper.toDetailCoinVo
import com.maxkor.feature.detail.impl.presentation.model.DetailCoinVo

private const val MAIN_IMAGE_SIZE = 180

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailScreen(
    detailCoinVo: DetailCoinVo,
    detailUiState: DetailUiState,
    coinExtraInfoInput: String,
    editCoinExtraInfoInput: (String) -> Unit,
    savePicture: (url: String, name: String) -> Unit,
    sharePicture: (url: String) -> Unit,
    createReminder: (
        coinName: String,
        coinPrice: String,
        coinImageUrl: String,
        hour: Int,
        minute: Int,
    ) -> Unit,
    addCoinExtraInfo: () -> Unit,
    saveCoinExtraInfo: (key: String, extraInfo: String) -> Unit,
    onTextSectionClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    val spacing = LocalSpacing.current
    val fontScaling = LocalFontScaling.current

    val timePickerState: TimePickerState = rememberTimePickerState()
    var shouldShowTimePicker by remember { mutableStateOf(false) }
    val showTimePicker: () -> Unit = {
        shouldShowTimePicker = true
    }
    val dismissTimePicker: () -> Unit = {
        shouldShowTimePicker = false
    }
    var timePickerMode: TimePickerMode by remember {
        mutableStateOf(TimePickerMode.Pick)
    }
    val swapTimePickerMode: () -> Unit = {
        timePickerMode = when (timePickerMode) {
            TimePickerMode.Pick -> TimePickerMode.Input
            TimePickerMode.Input -> TimePickerMode.Pick
        }
    }

    if (shouldShowTimePicker) {
        TimePickerSwitchable(
            timePickerState = timePickerState,
            isTimePickerExpanded = when (timePickerMode) {
                TimePickerMode.Pick -> true
                TimePickerMode.Input -> false
            },
            onToggleClick = swapTimePickerMode,
            onConfirm = { hour, minute ->
                createReminder(
                    detailCoinVo.name,
                    detailCoinVo.price,
                    detailCoinVo.imageUrl,
                    hour,
                    minute
                )
                dismissTimePicker()
            },
            onDecline = { dismissTimePicker() }
        )
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
            model = CoilImageLoader.loadSvgImage(
                context = context,
                modelUrl = detailCoinVo.imageUrl
            ),
            contentDescription = stringResource(
                com.maxkor.core.base.R.string.cryptocoin_picture
            ),
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
                onClick = { showTimePicker() }
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
                        text = {
                            ButtonText(
                                text = stringResource(R.string.save)
                            )
                        },
                        icon = {
                            Icon(
                                imageVector = CryptocoinsIcons.Done,
                                contentDescription = stringResource(
                                    com.maxkor.core.base.R.string.icon
                                )
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
                            .clickable { addCoinExtraInfo() }
                            .padding(spacing.spaceMedium)
                    )
            }
        }
    }
}

/**
 * Private sector
 */
private sealed class TimePickerMode {
    data object Pick : TimePickerMode()
    data object Input : TimePickerMode()
}

/**
 * Preview
 */
@Composable
@RawPreview
fun RunPreviewDetailScreen() = PreviewProvider(
    content = {
        DetailScreen(
            detailCoinVo = DetailCoin.testExemplar.toDetailCoinVo(),
            detailUiState = DetailUiState.ModeRead,
            coinExtraInfoInput = "",
            editCoinExtraInfoInput = {},
            savePicture = { _, _ -> },
            sharePicture = {},
            createReminder = { _, _, _, _, _ -> },
            saveCoinExtraInfo = { _, _ -> },
            addCoinExtraInfo = {},
            onTextSectionClick = {}
        )
    }
).DeviceRunnable()