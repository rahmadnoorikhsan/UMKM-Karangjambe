package com.karangjambe.umkm.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class User(

    @field:SerializedName("address")
    val address: String? = null,

    @field:SerializedName("is_active")
    val isActive: String? = null,

    @field:SerializedName("gender")
    val gender: String? = null,

    @field:SerializedName("postcode")
    val postcode: String? = null,

    @field:SerializedName("photo")
    val photo: String? = null,

    @field:SerializedName("created_at")
    val createdAt: String? = null,

    @field:SerializedName("updated_at")
    val updatedAt: String? = null,

    @field:SerializedName("role_id")
    val roleId: String? = null,

    @field:SerializedName("province_id")
    val provinceId: String? = null,

    @field:SerializedName("phone")
    val phone: String? = null,

    @field:SerializedName("name")
    val name: String? = null,

    @field:SerializedName("id")
    val id: Int? = null,

    @field:SerializedName("email")
    val email: String? = null,

    @field:SerializedName("username")
    val username: String? = null,

    @field:SerializedName("city_id")
    val cityId: String? = null
)