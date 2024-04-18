package com.maxkor.feature.coins.impl.presentation.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.maxkor.core.ui.components.composables.CryptocoinCard
import com.maxkor.core.ui.preview.PreviewProvider
import com.maxkor.core.ui.preview.annotations.RawPreview
import com.maxkor.feature.coins.impl.domain.model.Coin
import com.maxkor.feature.coins.impl.presentation.mapper.toCoinVo
import com.maxkor.feature.coins.impl.presentation.mapper.toCryptocoinVo
import com.maxkor.feature.coins.impl.presentation.model.CoinVo

@Composable
fun CoinCard(
    coinVo: CoinVo,
    onCardClick: () -> Unit,
    onFavoriteIconClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    CryptocoinCard(
        cryptocoinVo = coinVo.toCryptocoinVo(),
        onCardClick = onCardClick,
        onFavoriteIconClick = onFavoriteIconClick,
        modifier = modifier
    )
}

/**
 * Preview
 */
@Composable
@RawPreview
fun RunPreviewCoinCard() {
    PreviewProvider(
        content = {
            CoinCard(
                coinVo = Coin.testExemplar.toCoinVo(),
                onCardClick = { },
                onFavoriteIconClick = {}
            )
        }
    ).DeviceRunnable()
}