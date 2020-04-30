package com.brandontm.antojitos.ui.menu

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.brandontm.antojitos.R
import com.brandontm.antojitos.di.viewModel.ViewModelProviderFactory
import com.brandontm.antojitos.ui.cart.ShoppingCartViewModel
import com.brandontm.antojitos.base.BaseFragment
import com.brandontm.antojitos.ui.MainActivity
import kotlinx.android.synthetic.main.menu_fragment.*
import javax.inject.Inject

class MenuFragment : BaseFragment() {
    @Inject
    lateinit var viewModelProvider: ViewModelProviderFactory

    @Inject
    lateinit var application: Application

    private lateinit var viewModel: MenuViewModel
    private val shoppingCartViewModel by viewModels<ShoppingCartViewModel>({activity as MainActivity}) {
        viewModelProvider
    }

    private val adapter = ProductListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.menu_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(this, viewModelProvider)
            .get(MenuViewModel::class.java)

        val navHostFragment = NavHostFragment.findNavController(this)
        NavigationUI.setupWithNavController(toolbar, navHostFragment)

        fab_shopping_cart.setOnClickListener {
            if(shoppingCartViewModel.cart.value?.isNotEmpty() == true) {
                navigateToShoppingCart()
            } else {
                Toast.makeText(
                    application.applicationContext,
                    getString(R.string.no_product_in_cart),
                    Toast.LENGTH_LONG).show()
            }
        }

        initRecyclerView()
        subscribeObservers()

        viewModel.loadProducts()
    }

    private fun subscribeObservers() {
        viewModel.products.observe(viewLifecycleOwner) {
            val list = it.toMutableList()

            // TODO: Remove duplicating
            list.addAll(it)
            list.addAll(it)
            list.addAll(it)
            list.addAll(it)
            list.addAll(it)
            adapter.updateItems(list)
        }
    }

    private fun initRecyclerView() {
        adapter.onAddProductClicked = { product ->
            shoppingCartViewModel.addProduct(product)
            Toast
                .makeText(
                    application.applicationContext,
                    getString(R.string.product_added),
                    Toast.LENGTH_SHORT)
                .show()
        }
        rv_products.adapter = adapter

        // Show divider
        rv_products.addItemDecoration(DividerItemDecoration(rv_products.context, DividerItemDecoration.VERTICAL))

    }

    private fun navigateToShoppingCart() {
        val action = MenuFragmentDirections.actionMenuFragmentToShoppingCartFragment()
        this.findNavController().navigate(action)
    }
}
