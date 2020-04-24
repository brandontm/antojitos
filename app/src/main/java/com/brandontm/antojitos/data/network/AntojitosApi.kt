package com.brandontm.antojitos.data.network

import androidx.annotation.WorkerThread
import com.brandontm.antojitos.data.entity.Product
import retrofit2.http.GET

interface AntojitosApi {
    @WorkerThread
    @GET("products")
    suspend fun getProducts(): List<Product>
}