package com.brandontm.antojitos.ui.menu

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.brandontm.antojitos.data.entity.Product
import com.brandontm.antojitos.data.repository.ProductRepository
import com.brandontm.reliq.base.BaseViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class MenuViewModel @Inject constructor(private val productRepository: ProductRepository): BaseViewModel() {
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
}
