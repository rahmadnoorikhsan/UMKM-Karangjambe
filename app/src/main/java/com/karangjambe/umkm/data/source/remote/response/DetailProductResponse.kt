package com.karangjambe.umkm.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class DetailProductResponse(

	@field:SerializedName("data")
	val data: DetailProduct,

	@field:SerializedName("success")
	val success: Boolean? = null
)