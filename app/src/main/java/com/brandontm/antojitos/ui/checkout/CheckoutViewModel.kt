package com.brandontm.antojitos.ui.checkout

import androidx.lifecycle.viewModelScope
import com.brandontm.antojitos.base.BaseViewModel
import com.brandontm.antojitos.data.repository.CartRepository
import com.brandontm.antojitos.data.repository.OrderRepository
import com.brandontm.antojitos.util.SingleLiveEvent
import kotlinx.coroutines.launch
import javax.inject.Inject

class CheckoutViewModel @Inject constructor(
    private val orderRepository: OrderRepository,
    private val cartRepository: CartRepository
) : BaseViewModel() {
    val orderStatus = SingleLiveEvent<Boolean>()

    fun placeOrder(name: String, address: String, phoneNumber: String) {
        viewModelScope.launch {
            cartRepository.getCart().value?.let { cart ->
                orderRepository.saveOrder(name, address, phoneNumber, cart)
                orderStatus.value = true
            }
        }
    }
}
