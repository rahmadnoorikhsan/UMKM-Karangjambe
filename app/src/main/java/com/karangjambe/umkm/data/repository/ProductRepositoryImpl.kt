package com.karangjambe.umkm.data.repository

import com.karangjambe.umkm.data.source.remote.RemoteDataSource
import com.karangjambe.umkm.data.source.remote.response.DetailProduct
import com.karangjambe.umkm.data.source.remote.response.ProductItem
import com.karangjambe.umkm.domain.common.Result
import com.karangjambe.umkm.domain.repository.ProductRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepositoryImpl @Inject constructor(
    private val remoteDataSource: RemoteDataSource,
) : ProductRepository {
    override fun getProducts(): Flow<Result<List<ProductItem>>> = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getProducts()
            val result = response.data
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun getProductById(id: Int): Flow<Result<DetailProduct>> = flow {
        emit(Result.Loading())
        try {
            val response = remoteDataSource.getProductById(id)
            val result = response.data
            emit(Result.Success(result))
        } catch (e: Exception) {
            emit(Result.Error(e.message))
        }
    }.flowOn(Dispatchers.IO)

    override fun getSearch(query: String): Flow<Result<List<ProductItem>>> =
        flow {
            emit(Result.Loading())
            try {
                val response = remoteDataSource.getSearch(query)
                val result = response.data
                if (result != null) emit(Result.Success(result))
            } catch (e: Exception) {
                emit(Result.Error(e.message))
            }
        }.flowOn(Dispatchers.IO)
}