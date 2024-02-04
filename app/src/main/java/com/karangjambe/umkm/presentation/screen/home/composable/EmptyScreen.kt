package com.karangjambe.umkm.presentation.screen.home.composable

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.airbnb.lottie.compose.LottieAnimation
import com.airbnb.lottie.compose.LottieCompositionSpec
import com.airbnb.lottie.compose.LottieConstants
import com.airbnb.lottie.compose.rememberLottieComposition
import com.karangjambe.umkm.R
import com.karangjambe.umkm.ui.theme.UMKMTheme

@Composable
fun EmptyScreen(
    modifier: Modifier = Modifier,
) {
    val preloaderLottieComposition by rememberLottieComposition(
        LottieCompositionSpec.RawRes(
            R.raw.empty
        )
    )

    Box(
        modifier = modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LottieAnimation(
            composition = preloaderLottieComposition,
            iterations = LottieConstants.IterateForever,
            modifier = modifier
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun EmptyScreenPrev() {
    UMKMTheme {
        EmptyScreen()
    }
}