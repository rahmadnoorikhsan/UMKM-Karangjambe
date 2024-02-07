package com.karangjambe.umkm.domain.repository

import com.karangjambe.umkm.data.source.remote.response.DetailProduct
import com.karangjambe.umkm.domain.common.Result
import com.karangjambe.umkm.data.source.remote.response.ProductItem
import kotlinx.coroutines.flow.Flow

interface ProductRepository {

    fun getProducts(): Flow<Result<List<ProductItem>>>

    fun getProductById(id: Int): Flow<Result<DetailProduct>>

    fun getSearch(query: String): Flow<Result<List<ProductItem>>>
}