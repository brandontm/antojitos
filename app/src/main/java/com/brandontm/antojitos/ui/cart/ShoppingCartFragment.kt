package com.brandontm.antojitos.ui.cart

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView

import com.brandontm.antojitos.R
import com.brandontm.antojitos.di.viewModel.ViewModelProviderFactory
import com.brandontm.antojitos.base.BaseFragment
import com.brandontm.antojitos.ui.MainActivity
import kotlinx.android.synthetic.main.menu_fragment.toolbar
import kotlinx.android.synthetic.main.shopping_cart_fragment.*
import okhttp3.internal.toImmutableMap
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class ShoppingCartFragment : BaseFragment() {
    @Inject
    lateinit var viewModelProvider: ViewModelProviderFactory

    private val viewModel by viewModels<ShoppingCartViewModel>({activity as MainActivity }) {
        viewModelProvider
    }

    private val adapter = CartListAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.shopping_cart_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val navHostFragment = NavHostFragment.findNavController(this)
        NavigationUI.setupWithNavController(toolbar, navHostFragment)

        showCartProducts()

        viewModel.cart.observe(viewLifecycleOwner) {
            adapter.updateItems(it)

            var total = 0
            for((key, value) in it)
                total += key.price * value

            val moneyFormat = NumberFormat.getCurrencyInstance().apply {
                this.currency = Currency.getInstance("MXN")
                this.maximumFractionDigits = 0
            }
            lbl_total.text = moneyFormat.format(total)
        }
    }

    private fun showCartProducts() {
        rv_cart_products.adapter = adapter

        // Show divider
        rv_cart_products.addItemDecoration(DividerItemDecoration(rv_cart_products.context, DividerItemDecoration.VERTICAL))
        val swipeToDeleteCallback = object : SwipeToDeleteCallback(requireContext()) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                viewModel.removeProduct(adapter.getItemByPosition(viewHolder.adapterPosition)!!)
            }
        }
        val itemTouchHelper = ItemTouchHelper(swipeToDeleteCallback)
        itemTouchHelper.attachToRecyclerView(rv_cart_products)

        adapter.onQuantityChanged = { product, quantity ->
            viewModel.addProduct(product, quantity)
        }
    }
}