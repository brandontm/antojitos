package com.brandontm.antojitos.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.brandontm.antojitos.data.entity.Product
import com.brandontm.antojitos.data.repository.ProductRepository
import com.brandontm.antojitos.base.BaseViewModel
import com.brandontm.antojitos.data.repository.CartRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val productRepository: ProductRepository,
                                        private val cartRepository: CartRepository): BaseViewModel() {
    val cart: LiveData<Map<Product, Int>> = cartRepository.getCart()

    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    fun loadProducts() {
        viewModelScope.launch {
            productRepository.getProducts()
                .collect { productItems ->
                    _products.value = productItems
                }
        }
    }

    fun addProduct(product: Product) {
        val quantity = cart.value?.get(product)?.plus(1) ?: 1

        addProduct(product, quantity)
    }

    fun addProduct(product: Product, quantity: Int) {
        viewModelScope.launch {
            cartRepository.setInCart(product, quantity)
        }
    }

    fun removeProduct(product: Product) {
        viewModelScope.launch {
            cartRepository.removeFromCart(product)
        }
    }
}
