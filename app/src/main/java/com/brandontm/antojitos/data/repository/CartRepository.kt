package com.brandontm.antojitos.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.brandontm.antojitos.data.entity.Product
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CartRepository @Inject constructor() {
    private val cart: MutableLiveData<Map<Product, Int>> = MutableLiveData(mapOf())

    fun getCart(): LiveData<Map<Product, Int>> {
        return cart
    }

    fun setInCart(product: Product, quantity: Int) {
        val items: MutableMap<Product, Int> = cart.value?.toMutableMap() ?: mutableMapOf()
        items[product] = quantity

        cart.value = items
    }

    fun removeFromCart(product: Product) {
        val items: MutableMap<Product, Int> = cart.value?.toMutableMap() ?: mutableMapOf()
        items.remove(product)

        cart.value = items
    }
}