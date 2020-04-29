package com.brandontm.antojitos.ui.cart

import android.app.Application
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.recyclerview.widget.RecyclerView
import com.brandontm.antojitos.AntojitosApp
import com.brandontm.antojitos.R
import com.brandontm.antojitos.data.entity.Product
import com.brandontm.antojitos.di.application.ApplicationComponent
import com.brandontm.antojitos.di.application.DaggerApplicationComponent
import dagger.android.AndroidInjection
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasAndroidInjector
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.layout_cart_list_item.view.*
import java.text.NumberFormat
import java.util.*
import javax.inject.Inject

class CartListAdapter : RecyclerView.Adapter<CartListAdapter.ViewHolder>() {
    private var items = mutableMapOf<Product, Int>()
    private val moneyFormat = NumberFormat.getCurrencyInstance().apply {
        this.currency = Currency.getInstance("MXN")
        this.maximumFractionDigits = 0
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_cart_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val quantitiesAdapter =
            ArrayAdapter<Int>(
                holder.itemView.context,
                android.R.layout.simple_dropdown_item_1line,
                holder.itemView.resources.getIntArray(R.array.quantities).toList()
            )

        val product = items.keys.toTypedArray()[position]

        with(holder.itemView) {
            lbl_product_name.text = product.name

            spn_product_quantity.setAdapter(quantitiesAdapter)
            spn_product_quantity.setText(items[product].toString(), false)
            lbl_product_accumulative_price.text = moneyFormat.format(product.price * items[product]!!)
        }
    }

    fun updateItems(items: Map<Product, Int>) {
        this.items = items.toMutableMap()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}