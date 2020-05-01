package com.brandontm.antojitos.data.repository

import com.brandontm.antojitos.data.entity.Product
import com.brandontm.antojitos.data.network.AntojitosApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ProductRepository @Inject constructor(private val antojitosApi: AntojitosApi) {
    fun getProducts(): Flow<List<Product>> = flow {
        emit(antojitosApi.getProducts())
    }
}