package com.brandontm.antojitos.data.repository

import com.brandontm.antojitos.data.entity.Product
import com.brandontm.antojitos.data.network.AntojitosApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ProductRepository @Inject constructor(private val antojitosApi: AntojitosApi) {
    fun getProducts(): Flow<List<Product>> = flow {
        emit(antojitosApi.getProducts())
    }

    companion object {
        private var instance: ProductRepository? = null

        fun getInstance(antojitosApi: AntojitosApi): ProductRepository {
            if (instance == null) {
                instance = ProductRepository(antojitosApi)
            }
            return instance!!
        }
    }
}