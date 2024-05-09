package com.maxkor.feature.detail.impl.presentation.screen

import com.maxkor.feature.detail.impl.domain.model.DetailCoin
import com.maxkor.feature.detail.impl.presentation.mapper.toDetailCoinVo
import com.maxkor.feature.detail.impl.presentation.model.DetailCoinVo

sealed class DetailUiState() {
    data class ModeRead(
        val detailCoinVo: DetailCoinVo = DetailCoin.testExemplar.toDetailCoinVo(),
    ) : DetailUiState()

    data class ModeEdit(
        val detailCoinVo: DetailCoinVo = DetailCoin.testExemplar.toDetailCoinVo(),
    ) : DetailUiState()

}