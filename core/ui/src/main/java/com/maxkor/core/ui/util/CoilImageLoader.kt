package com.maxkor.core.ui.util

import android.content.Context
import coil.decode.SvgDecoder
import coil.request.ImageRequest

object CoilImageLoader {

    fun loadSvgImage(
        context: Context,
        modelUrl: String,
    ) = ImageRequest.Builder(context)
        .data(modelUrl)
        .decoderFactory(SvgDecoder.Factory())
        .build()
}