package com.brandontm.antojitos.ui.cart

import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.brandontm.antojitos.base.BaseViewModel
import com.brandontm.antojitos.data.entity.Product
import com.brandontm.antojitos.data.repository.CartRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class ShoppingCartViewModel @Inject constructor(private val cartRepository: CartRepository) : BaseViewModel() {
    val cart: LiveData<Map<Product, Int>> get() = cartRepository.getCart()

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
