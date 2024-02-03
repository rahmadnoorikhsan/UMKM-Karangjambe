package com.karangjambe.umkm.presentation.screen.home.composable

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import com.karangjambe.umkm.utils.Converter.toCurrencyFormat
import com.karangjambe.umkm.utils.imageUrl

@Composable
fun ProductItem(
    imageUrl: String,
    name: String,
    price: String,
    discount: String?,
    navigateToDetail: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val priceInt = price.toInt()
    val discountInt = discount?.toInt()

    ConstraintLayout(
        modifier = modifier
            .fillMaxSize()
            .clickable { navigateToDetail() }
    ) {
        val (imageRefs, nameRefs, priceDiscountRefs, discountRefs, originalPriceRefs) = createRefs()

        AsyncImage(
            model = imageUrl(imageUrl),
            contentDescription = "$name Image",
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .height(164.dp)
                .clip(MaterialTheme.shapes.medium)
                .constrainAs(imageRefs) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        Text(
            text = name,
            style = MaterialTheme.typography.bodyMedium.copy(
                fontWeight = FontWeight.Medium
            ),
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(nameRefs) {
                    top.linkTo(imageRefs.bottom, 8.dp)
                    start.linkTo(imageRefs.start)
                    end.linkTo(imageRefs.end)
                }
        )
        if (discountInt != null) {
            val totalDiscount = priceInt * discountInt / 100
            val total = priceInt - totalDiscount

            Text(
                text = total.toCurrencyFormat(),
                style = MaterialTheme.typography.bodyLarge.copy(
                    fontWeight = FontWeight.SemiBold,
                    color = MaterialTheme.colorScheme.primary
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(priceDiscountRefs) {
                        top.linkTo(nameRefs.bottom)
                        start.linkTo(nameRefs.start)
                        end.linkTo(nameRefs.end)
                    }
            )
            Text(
                text = priceInt.toCurrencyFormat(),
                style = MaterialTheme.typography.labelMedium.copy(
                    textDecoration = TextDecoration.LineThrough,
                    fontWeight = FontWeight.Light,
                ),
                modifier = Modifier
                    .constrainAs(originalPriceRefs) {
                        top.linkTo(priceDiscountRefs.bottom)
                        start.linkTo(priceDiscountRefs.start)
                    }
            )
            Text(
                text = "-$discount%",
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Color.Red,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .clip(MaterialTheme.shapes.extraSmall)
                    .background(Color.Yellow)
                    .padding(horizontal = 2.dp)
                    .constrainAs(discountRefs) {
                        top.linkTo(originalPriceRefs.top)
                        bottom.linkTo(originalPriceRefs.bottom)
                        start.linkTo(originalPriceRefs.end, 2.dp)
                    }
            )
        } else {
            Text(
                text = priceInt.toCurrencyFormat(),
                style = MaterialTheme.typography.bodyMedium.copy(
                    fontWeight = FontWeight.SemiBold,
                ),
                modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(originalPriceRefs) {
                        top.linkTo(nameRefs.bottom)
                        start.linkTo(nameRefs.start)
                        end.linkTo(nameRefs.end)
                    }
            )
        }

    }
}