package com.karangjambe.umkm.presentation.screen.home

import com.karangjambe.umkm.data.source.remote.response.ProductItem

data class HomeState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val statusMessage: String? = null,
    val query: String = "",
    val products: List<ProductItem> = emptyList(),
)