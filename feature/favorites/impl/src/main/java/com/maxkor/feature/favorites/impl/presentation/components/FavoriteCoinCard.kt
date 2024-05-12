package com.maxkor.feature.favorites.impl.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.maxkor.core.ui.components.ConfirmationDialog
import com.maxkor.core.ui.components.CryptocoinCard
import com.maxkor.core.ui.preview.PreviewProvider
import com.maxkor.core.ui.preview.annotations.RawPreview
import com.maxkor.feature.favorites.impl.domain.model.FavoriteCoin
import com.maxkor.feature.favorites.impl.R
import com.maxkor.feature.favorites.impl.presentation.mapper.toCryptocoinVo
import com.maxkor.feature.favorites.impl.presentation.mapper.toFavoriteCoinVo
import com.maxkor.feature.favorites.impl.presentation.model.FavoriteCoinVo

@Composable
fun FavoriteCoinCard(
    favoriteCoinVo: FavoriteCoinVo,
    onCardClick: () -> Unit,
    onFavoriteIconClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    var shouldShowDialog by remember { mutableStateOf(false) }
    val showDialog: () -> Unit = { shouldShowDialog = true }
    val dismissDialog: () -> Unit = { shouldShowDialog = false }

    CryptocoinCard(
        cryptocoinVo = favoriteCoinVo.toCryptocoinVo(),
        onCardClick = onCardClick,
        onFavoriteIconClick = showDialog,
        modifier = modifier
    )

    if (shouldShowDialog) {
        ConfirmationDialog(
            title = stringResource(R.string.remove_coin_confirmation),
            onCancel = { dismissDialog() },
            onConfirm = {
                onFavoriteIconClick()
                dismissDialog()
            }
        )
    }
}

/**
 * Preview
 */
@Composable
@RawPreview
fun RunPreviewFavoriteCoinCard() = PreviewProvider(
    content = {
        FavoriteCoinCard(
            favoriteCoinVo = FavoriteCoin.testExemplar
                .toFavoriteCoinVo(),
            onCardClick = {},
            onFavoriteIconClick = {}
        )
    }
).DeviceRunnable()