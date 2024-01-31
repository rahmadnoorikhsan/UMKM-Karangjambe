package com.karangjambe.umkm.data.source.remote.retrofit

import com.karangjambe.umkm.data.source.remote.response.DetailProductResponse
import com.karangjambe.umkm.data.source.remote.response.ProductsResponse
import com.karangjambe.umkm.data.source.remote.response.SearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("api/products")
    suspend fun getProducts() : ProductsResponse

    @GET("api/products/{id}")
    suspend fun getProductById(
        @Path("id") id: Int
    ) : DetailProductResponse

    @GET("api/search")
    suspend fun getSearch(
        @Query("query") query: String
    ): SearchResponse
}