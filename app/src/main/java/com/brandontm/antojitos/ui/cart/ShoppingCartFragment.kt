package com.brandontm.antojitos.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration

import com.brandontm.antojitos.R
import com.brandontm.antojitos.di.viewModel.ViewModelProviderFactory
import com.brandontm.antojitos.base.BaseFragment
import kotlinx.android.synthetic.main.menu_fragment.*
import kotlinx.android.synthetic.main.menu_fragment.toolbar
import kotlinx.android.synthetic.main.shopping_cart_fragment.*
import javax.inject.Inject

class ShoppingCartFragment : BaseFragment() {
    @Inject
    lateinit var viewModelProvider: ViewModelProviderFactory

    private lateinit var viewModel: ShoppingCartViewModel

    private val adapter = CartListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.shopping_cart_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, viewModelProvider)
            .get(ShoppingCartViewModel::class.java)
        val navHostFragment = NavHostFragment.findNavController(this)
        NavigationUI.setupWithNavController(toolbar, navHostFragment)

        initRecyclerView()

        viewModel.cartItems.observe(viewLifecycleOwner) {
            adapter.updateItems(it)
        }
    }

    private fun initRecyclerView() {
        adapter.onQuantityChanged = { product, quantity ->
            viewModel.addProductToCart(product, quantity)
        }
        rv_cart_products.adapter = adapter

        // Show divider
        rv_cart_products.addItemDecoration(DividerItemDecoration(rv_cart_products.context, DividerItemDecoration.VERTICAL))
    }
}
