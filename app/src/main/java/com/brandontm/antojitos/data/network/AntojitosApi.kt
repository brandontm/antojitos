package com.brandontm.antojitos.data.network

import androidx.annotation.WorkerThread
import com.brandontm.antojitos.data.entity.Order
import com.brandontm.antojitos.data.entity.Product
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface AntojitosApi {
    @WorkerThread
    @GET("products")
    suspend fun getProducts(): List<Product>

    @WorkerThread
    @POST("order")
    suspend fun saveOrder(@Body order: Order)
}
