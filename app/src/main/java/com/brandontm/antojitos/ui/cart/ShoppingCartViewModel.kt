package com.brandontm.antojitos.ui.cart

import androidx.collection.arrayMapOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.brandontm.antojitos.data.entity.Product
import com.brandontm.antojitos.data.repository.ProductRepository
import com.brandontm.antojitos.base.BaseViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShoppingCartViewModel @Inject constructor(private val productRepository: ProductRepository) : BaseViewModel() {
    private val _cartItems = MutableLiveData<Map<Product, Int>>()
    val cartItems: LiveData<Map<Product, Int>> get() = _cartItems

    fun addProductToCart(product: Product) {

        viewModelScope.launch {
            val items: MutableMap<Product, Int> = _cartItems.value?.toMutableMap() ?: arrayMapOf()
            val quantity = items[product]?.plus(1) ?: 1

            this@ShoppingCartViewModel.addProductToCart(product, quantity)
        }
    }

    fun addProductToCart(product: Product, quantity: Int) {
        viewModelScope.launch {
            val items: MutableMap<Product, Int> = _cartItems.value?.toMutableMap() ?: arrayMapOf()
            items[product] = quantity

            _cartItems.value = items
        }
    }
}
