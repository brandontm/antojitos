package com.brandontm.antojitos.data.repository

import com.brandontm.antojitos.data.entity.Order
import com.brandontm.antojitos.data.entity.Product
import com.brandontm.antojitos.data.network.AntojitosApi
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class OrderRepository @Inject constructor(private val antojitosApi: AntojitosApi) {
    suspend fun saveOrder(
        name: String, address: String, phoneNumber: String, cart: Map<Product, Int>
    ): Unit =
        antojitosApi.saveOrder(Order(name, address, phoneNumber, cart.mapKeys { entry ->
            entry.key.id
        }))
}
