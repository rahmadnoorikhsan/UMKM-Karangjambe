package com.karangjambe.umkm.presentation.screen.detail

import com.karangjambe.umkm.data.source.remote.response.DetailProduct

data class DetailState(
    val isLoading: Boolean = false,
    val isError: Boolean = false,
    val statusMessage: String? = null,
    val detailProduct: DetailProduct = DetailProduct(),
)
