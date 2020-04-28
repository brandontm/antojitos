package com.brandontm.antojitos.ui.cart

import com.brandontm.antojitos.data.repository.ProductRepository
import com.brandontm.reliq.base.BaseViewModel
import javax.inject.Inject

class ShoppingCartViewModel @Inject constructor(private val productRepository: ProductRepository) : BaseViewModel() {

}
