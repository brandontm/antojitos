package com.brandontm.antojitos.ui.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.lifecycle.observe
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import com.brandontm.antojitos.R
import com.brandontm.antojitos.di.viewModel.ViewModelProviderFactory
import com.brandontm.reliq.base.BaseFragment
import kotlinx.android.synthetic.main.menu_fragment.*
import javax.inject.Inject

class MenuFragment : BaseFragment() {
    @Inject
    lateinit var viewModelProvider: ViewModelProviderFactory

    private lateinit var viewModel: MenuViewModel

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
            navigateToShoppingCart()
        }

        val adapter = ProductListAdapter()

        // Show divider
        rv_products.addItemDecoration(DividerItemDecoration(rv_products.context, DividerItemDecoration.VERTICAL))

        rv_products.adapter = adapter

        viewModel.loadProducts()

        viewModel.products.observe(viewLifecycleOwner) {
            val list = it.toMutableList()
            list.addAll(it)
            list.addAll(it)
            list.addAll(it)
            list.addAll(it)
            list.addAll(it)
            adapter.updateItems(list)
        }
    }

    private fun navigateToShoppingCart() {
        val action = MenuFragmentDirections.actionMenuFragmentToShoppingCartFragment()
        this.findNavController().navigate(action)
    }
}
