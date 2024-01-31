package com.karangjambe.umkm.data.source.remote

import com.karangjambe.umkm.data.source.remote.retrofit.ApiService
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class RemoteDataSource @Inject constructor(
    private val apiService: ApiService
) {

    suspend fun getProducts() = apiService.getProducts()

    suspend fun getProductById(id: Int) = apiService.getProductById(id)

    suspend fun getSearch(query: String) = apiService.getSearch(query)
}