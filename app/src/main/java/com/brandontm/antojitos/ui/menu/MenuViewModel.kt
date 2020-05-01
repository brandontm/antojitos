package com.brandontm.antojitos.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.brandontm.antojitos.Config
import com.brandontm.antojitos.data.entity.Product
import com.brandontm.antojitos.data.repository.ProductRepository
import com.brandontm.antojitos.base.BaseViewModel
import com.brandontm.antojitos.data.repository.CartRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val productRepository: ProductRepository,
                                        private val cartRepository: CartRepository): BaseViewModel() {
    private val _products = MutableLiveData<List<Product>>()
    val products: LiveData<List<Product>> get() = _products

    private val _saveStatus = MutableLiveData<Boolean>()
    val saveStatus: LiveData<Boolean> get() = _saveStatus

    val cart: LiveData<Map<Product, Int>> get() = cartRepository.getCart()

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

        if(quantity <= Config.MAX_PRODUCT_QUANTITY) {
            addProduct(product, quantity)
            _saveStatus.value = true
        } else {
            _saveStatus.value = false
        }
    }

    fun addProduct(product: Product, quantity: Int) {
        viewModelScope.launch {
            cartRepository.setInCart(product, quantity)
        }
    }
}
