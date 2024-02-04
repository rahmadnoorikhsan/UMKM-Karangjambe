package com.karangjambe.umkm.presentation.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.karangjambe.umkm.R
import com.karangjambe.umkm.data.source.remote.response.ProductItem
import com.karangjambe.umkm.presentation.navigation.Screen
import com.karangjambe.umkm.presentation.screen.home.composable.EmptyScreen
import com.karangjambe.umkm.presentation.screen.home.composable.ProductItem
import com.karangjambe.umkm.presentation.screen.home.composable.ProductItemShimmer

@Composable
fun HomeScreen(
    navController: NavController,
    showSnackBarMessage: (String) -> Unit,
    homeViewModel: HomeViewModel = hiltViewModel(),
) {
    val state by homeViewModel.state.collectAsStateWithLifecycle()

    state.statusMessage?.let {
        LaunchedEffect(state.isError) {
            showSnackBarMessage(it)
        }
    }

    HomeContent(
        navController = navController,
        products = state.products,
        query = state.query,
        isLoading = state.isLoading,
        onQueryChange = {
            homeViewModel.onEvent(HomeEvent.OnQueryChange(it))
            homeViewModel.onEvent(HomeEvent.OnSearch(it))
        },
        onSearch = {
            homeViewModel.onEvent(HomeEvent.OnSearch(it))
        }
    )
}

@Composable
fun HomeContent(
    query: String,
    isLoading: Boolean,
    onQueryChange: (String) -> Unit,
    onSearch: (String) -> Unit,
    navController: NavController,
    products: List<ProductItem>,
    modifier: Modifier = Modifier,
) {

    ConstraintLayout(modifier = modifier) {
        val (bannerRefs, searchRefs, listRefs) = createRefs()
        val focusManager = LocalFocusManager.current

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
        TextField(
            value = query,
            onValueChange = onQueryChange,
            placeholder = {
                Text(text = "Cari Produk")
            },
            maxLines = 1,
            trailingIcon = {
                if (query.isNotBlank()) {
                    IconButton(onClick = { onQueryChange("") }) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Clear Icon")
                    }
                }
            },
            leadingIcon = {
                Icon(imageVector = Icons.Default.Search, contentDescription = "Search Icon")
            },
            colors = TextFieldDefaults.colors(
                disabledTextColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                disabledIndicatorColor = Color.Transparent
            ),
            shape = MaterialTheme.shapes.medium,
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search,
            ),
            keyboardActions = KeyboardActions(
                onSearch = {
                    onSearch(query)
                    focusManager.clearFocus()
                }
            ),
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp)
                .constrainAs(searchRefs) {
                    top.linkTo(bannerRefs.bottom)
                    bottom.linkTo(bannerRefs.bottom)
                    start.linkTo(bannerRefs.start)
                    end.linkTo(bannerRefs.end)
                }
        )
        if (isLoading) {
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
                item {
                    ProductItemShimmer()
                }
                item {
                    ProductItemShimmer()
                }
                item {
                    ProductItemShimmer()
                }
                item {
                    ProductItemShimmer()

                }
            }
        } else {
            if (products.isEmpty()) {
                EmptyScreen(
                    modifier = Modifier
                        .constrainAs(listRefs) {
                            top.linkTo(searchRefs.bottom)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                            bottom.linkTo(parent.bottom, 32.dp)
                        }
                )
            } else {
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
    }
}