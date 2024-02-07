package com.karangjambe.umkm.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class SearchResponse(

	@field:SerializedName("data")
	val data: List<ProductItem>? = null,

	@field:SerializedName("success")
	val success: Boolean? = null
)