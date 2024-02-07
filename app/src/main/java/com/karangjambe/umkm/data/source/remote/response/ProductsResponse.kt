package com.karangjambe.umkm.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class ProductsResponse(

	@field:SerializedName("data")
	val data: List<ProductItem>,

	@field:SerializedName("success")
	val success: Boolean? = null
)