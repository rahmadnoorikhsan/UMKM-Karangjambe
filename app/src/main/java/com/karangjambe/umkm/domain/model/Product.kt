package com.karangjambe.umkm.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Product(
    val id: Int,
    val name: String,
    val imageUrl: String,
    val price: Int,
    val discount: Int? = null
) : Parcelable