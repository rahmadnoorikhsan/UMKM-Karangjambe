package com.karangjambe.umkm.presentation.screen.detail

import android.content.Context
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.karangjambe.umkm.data.source.remote.response.DetailProduct
import com.karangjambe.umkm.utils.Converter.toCurrencyFormat
import com.karangjambe.umkm.utils.imageUrl
import com.karangjambe.umkm.utils.openWhatsAppChat

@Composable
fun DetailScreen(
    id: Int,
    navController: NavController,
    detailViewModel: DetailViewModel = hiltViewModel(),
) {
    val context = LocalContext.current
    val state by detailViewModel.state.collectAsStateWithLifecycle()

    LaunchedEffect(key1 = id) {
        detailViewModel.onEvent(DetailEvent.OnGetDetailProduct(id))
    }

    DetailContent(
        navController = navController,
        detailProduct = state.detailProduct,
        context = context
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailContent(
    navController: NavController,
    detailProduct: DetailProduct,
    context: Context,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(modifier = modifier.fillMaxSize()) {
        val (imageRefs, topBarRefs, titleRefs, priceRefs, priceDiscountRefs, discountRefs, descriptionRefs, bottomBarRefs) = createRefs()

        val priceInt = detailProduct.price?.toInt() ?: 0
        val discountInt = detailProduct.discount?.toInt()

        AsyncImage(
            model = detailProduct.image?.let { image -> imageUrl(image) },
            contentDescription = detailProduct.name,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .fillMaxWidth()
                .height(320.dp)
                .constrainAs(imageRefs) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        TopAppBar(
            title = {},
            colors = TopAppBarDefaults.topAppBarColors(
                containerColor = Color.Transparent.copy(alpha = 0f),
                navigationIconContentColor = Color.White,
                actionIconContentColor = Color.White,
            ),
            navigationIcon = {
                IconButton(
                    onClick = { navController.navigateUp() },
                    modifier = Modifier
                        .clip(MaterialTheme.shapes.extraLarge)
                        .background(
                            Color.Transparent.copy(
                                alpha = 0.5f
                            )
                        )
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back Icon"
                    )
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(topBarRefs) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start, 8.dp)
                    end.linkTo(parent.end)
                }
        )
        if (discountInt != null) {
            val totalDiscount = priceInt * discountInt / 100
            val total = priceInt - totalDiscount
            Text(
                text = total.toCurrencyFormat(),
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.constrainAs(priceRefs) {
                    top.linkTo(imageRefs.bottom, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                }
            )
            Text(
                text = priceInt.toCurrencyFormat(),
                style = MaterialTheme.typography.titleSmall.copy(
                    textDecoration = TextDecoration.LineThrough,
                    fontWeight = FontWeight.Light,
                ),
                modifier = Modifier
                    .constrainAs(priceDiscountRefs) {
                        bottom.linkTo(priceRefs.bottom)
                        start.linkTo(priceRefs.end, 8.dp)
                    }
                    .offset(y = -(2).dp)
            )
            Text(
                text = "-${detailProduct.discount}%",
                style = MaterialTheme.typography.titleSmall.copy(
                    color = Color.Red,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .offset(y = -(2).dp)
                    .clip(MaterialTheme.shapes.extraSmall)
                    .background(Color.Yellow)
                    .padding(horizontal = 2.dp)
                    .constrainAs(discountRefs) {
                        bottom.linkTo(priceDiscountRefs.bottom)
                        start.linkTo(priceDiscountRefs.end, 8.dp)
                    }
            )
        } else {
            Text(
                text = priceInt.toCurrencyFormat(),
                style = MaterialTheme.typography.headlineSmall.copy(
                    color = MaterialTheme.colorScheme.primary,
                    fontWeight = FontWeight.SemiBold
                ),
                modifier = Modifier.constrainAs(priceRefs) {
                    top.linkTo(imageRefs.bottom, 16.dp)
                    start.linkTo(parent.start, 16.dp)
                }
            )
        }
        Text(
            text = detailProduct.name ?: "",
            style = MaterialTheme.typography.bodyLarge.copy(
                fontWeight = FontWeight.Medium,
            ),
            modifier = Modifier.constrainAs(titleRefs) {
                top.linkTo(priceRefs.bottom, 16.dp)
                start.linkTo(parent.start, 16.dp)
            }
        )
        Text(
            text = detailProduct.description ?: "",
            style = MaterialTheme.typography.bodyMedium.copy(
                textAlign = TextAlign.Justify
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .constrainAs(descriptionRefs) {
                    top.linkTo(titleRefs.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(MaterialTheme.colorScheme.surface)
                .constrainAs(bottomBarRefs) {
                    bottom.linkTo(parent.bottom)
                }
        ) {
            Button(
                onClick = { openWhatsAppChat(context, "+62 ${detailProduct.user?.phone}", "Halo") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Text(text = "Pesan")
            }
        }
    }
}
