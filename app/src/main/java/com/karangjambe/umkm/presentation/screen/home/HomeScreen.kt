package com.karangjambe.umkm.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.karangjambe.umkm.R
import com.karangjambe.umkm.data.source.remote.response.ProductItem
import com.karangjambe.umkm.presentation.navigation.Screen
import com.karangjambe.umkm.presentation.screen.home.composable.ProductItem

@Composable
fun HomeScreen(
    navController: NavController,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val state by homeViewModel.state.collectAsStateWithLifecycle()

    HomeContent(
        navController = navController,
        products = state.products
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContent(
    navController: NavController,
    products: List<ProductItem>,
    modifier: Modifier = Modifier,
) {

    ConstraintLayout(modifier = modifier) {
        val (bannerRefs, searchRefs, listRefs) = createRefs()

        Image(
            painter = painterResource(R.drawable.banner),
            contentDescription = "Banner",
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth()
                .constrainAs(bannerRefs) {
                    top.linkTo(parent.top)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                }
        )
        SearchBar(
            query = "",
            onQueryChange = {},
            onSearch = {},
            active = false,
            onActiveChange = {},
            shape = MaterialTheme.shapes.large,
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            },
            placeholder = {
                Text(text = "Cari Produk")
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(searchRefs) {
                    top.linkTo(bannerRefs.bottom)
                    bottom.linkTo(bannerRefs.bottom)
                    start.linkTo(bannerRefs.start)
                    end.linkTo(bannerRefs.end)
                }
                .offset(y = -(20).dp)
        ) {}
        LazyVerticalGrid(
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .constrainAs(listRefs) {
                    top.linkTo(searchRefs.bottom, 16.dp)
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    bottom.linkTo(parent.bottom)
                }
        ) {
            items(products) {
                ProductItem(
                    imageUrl = it.image ?: "",
                    name = it.name ?: "",
                    price = it.price ?: "",
                    discount = it.discount,
                    navigateToDetail = {
                        navController.navigate(Screen.Detail.createRoute(it.id ?: 0))
                    }
                )
            }
        }
    }
}