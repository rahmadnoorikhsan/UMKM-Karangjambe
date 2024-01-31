package com.karangjambe.umkm.data.source.remote.response

import com.google.gson.annotations.SerializedName


data class ProductItem(

    @field:SerializedName("image")
    val image: String? = null,

    @field:SerializedName("description")
    val description: String? = null,

    @field:SerializedName("discount")
    val discount: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("condition")
    val condition: String? = null,

    @field:SerializedName("category_id")
    val categoryId: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("price")
    val price: String? = null,

    @field:SerializedName("qty")
    val qty: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("tag_id")
    val tagId: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("slug")
    val slug: String? = null,

    @field:SerializedName("seller_id")
    val sellerId: String? = null
)