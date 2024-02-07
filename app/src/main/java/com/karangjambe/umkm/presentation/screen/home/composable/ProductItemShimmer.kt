package com.karangjambe.umkm.presentation.screen.home.composable

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.karangjambe.umkm.ui.theme.UMKMTheme

@Composable
fun ProductItemShimmer(
    modifier: Modifier = Modifier,
) {
    val shimmerColor = listOf(
        Color.LightGray.copy(0.6f),
        Color.LightGray.copy(0.2f),
        Color.LightGray.copy(0.6f),
    )

    val transition = rememberInfiniteTransition(label = "")
    val translateAnim = transition.animateFloat(
        initialValue = 0f,
        targetValue = 1000f,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = 1000,
                easing = FastOutSlowInEasing
            ),
            repeatMode = RepeatMode.Reverse
        ), label = "Animate"
    )

    val brush = Brush.linearGradient(
        colors = shimmerColor,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
    ) {
        val (imageRefs, nameRefs, priceDiscountRefs, discountRefs, originalPriceRefs) = createRefs()

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(164.dp)
                .clip(MaterialTheme.shapes.medium)
                .background(brush)
                .constrainAs(imageRefs) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(16.dp)
                .background(brush)
                .constrainAs(nameRefs) {
                    top.linkTo(imageRefs.bottom, 8.dp)
                    start.linkTo(imageRefs.start)
                    end.linkTo(imageRefs.end)
                }
        )
        Spacer(
            modifier = Modifier
                .fillMaxWidth(0.5f)
                .height(16.dp)
                .background(brush)
                .constrainAs(priceDiscountRefs) {
                    top.linkTo(nameRefs.bottom, 4.dp)
                    start.linkTo(nameRefs.start)
                }
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .width(56.dp)
                .background(brush)
                .constrainAs(originalPriceRefs) {
                    top.linkTo(priceDiscountRefs.bottom, 4.dp)
                    start.linkTo(priceDiscountRefs.start)
                }
        )
        Spacer(
            modifier = Modifier
                .height(16.dp)
                .width(32.dp)
                .clip(MaterialTheme.shapes.extraSmall)
                .background(brush)
                .padding(horizontal = 2.dp)
                .constrainAs(discountRefs) {
                    top.linkTo(originalPriceRefs.top)
                    bottom.linkTo(originalPriceRefs.bottom)
                    start.linkTo(originalPriceRefs.end, 4.dp)
                }
        )
    }
}

@Preview
@Composable
fun ProductItemShimmerPreview() {
    UMKMTheme {
        ProductItemShimmer()
    }
}