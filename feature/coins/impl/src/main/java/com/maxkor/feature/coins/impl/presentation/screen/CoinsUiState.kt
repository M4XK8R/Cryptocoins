/*
 * Copyright (C) 2023 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.maxkor.feature.coins.impl.presentation.screen

import com.maxkor.feature.coins.impl.presentation.model.CryptocoinVo

sealed interface CoinsUiState {

    data object Loading : CoinsUiState

    data class Success(val coins: List<CryptocoinVo>) : CoinsUiState
}
