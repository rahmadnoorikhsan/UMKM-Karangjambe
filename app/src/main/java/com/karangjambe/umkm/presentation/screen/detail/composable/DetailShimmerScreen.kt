package com.karangjambe.umkm.presentation.screen.detail.composable

import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
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
fun DetailShimmerScreen(
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
        ), label = ""
    )

    val brush = Brush.linearGradient(
        colors = shimmerColor,
        start = Offset.Zero,
        end = Offset(x = translateAnim.value, y = translateAnim.value)
    )

    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (imageRefs, topBarRefs, titleRefs, priceRefs, priceDiscountRefs, discountRefs, descriptionRefs, bottomBarRefs) = createRefs()

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .background(brush)
                .constrainAs(imageRefs) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Spacer(
            modifier = Modifier
                .width(40.dp)
                .height(40.dp)
                .clip(MaterialTheme.shapes.extraLarge)
                .background(brush)
                .constrainAs(topBarRefs) {
                    top.linkTo(parent.top, 48.dp)
                    start.linkTo(parent.start, 8.dp)
                }
        )
        Spacer(
            modifier = Modifier
                .background(brush)
                .height(24.dp)
                .width(80.dp)
                .constrainAs(priceRefs) {
                    top.linkTo(imageRefs.bottom, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                }
        )
        Spacer(
            modifier = Modifier
                .background(brush)
                .width(72.dp)
                .height(16.dp)
                .constrainAs(priceDiscountRefs) {
                    bottom.linkTo(priceRefs.bottom)
                    start.linkTo(priceRefs.end, 8.dp)
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
                    bottom.linkTo(priceDiscountRefs.bottom)
                    start.linkTo(priceDiscountRefs.end, 8.dp)
                }
        )
        Spacer(
            modifier = Modifier
                .background(brush)
                .fillMaxWidth(0.5f)
                .height(16.dp)
                .constrainAs(titleRefs) {
                    top.linkTo(priceRefs.bottom, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                }
        )
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .constrainAs(descriptionRefs) {
                    top.linkTo(titleRefs.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        ) {
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(brush)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(brush)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(brush)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(brush)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(brush)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(brush)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(brush)
            )
            Spacer(modifier = Modifier.height(4.dp))
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(16.dp)
                    .background(brush)
            )
        }
        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
                .height(40.dp)
                .clip(MaterialTheme.shapes.extraLarge)
                .background(brush)
                .constrainAs(bottomBarRefs) {
                    bottom.linkTo(parent.bottom)
                }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
fun DetailShimmerScreenPrev() {
    UMKMTheme {
        DetailShimmerScreen()
    }
}