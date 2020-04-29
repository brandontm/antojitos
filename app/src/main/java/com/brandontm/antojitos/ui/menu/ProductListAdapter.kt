package com.brandontm.antojitos.ui.menu

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.brandontm.antojitos.R
import com.brandontm.antojitos.data.entity.Product
import kotlinx.android.synthetic.main.layout_product_list_item.view.*
import java.text.NumberFormat
import java.util.*

class ProductListAdapter : RecyclerView.Adapter<ProductListAdapter.ViewHolder>() {
    private var items = mutableListOf<Product>()
    var onAddProductClicked:((product: Product) -> Unit)? = null


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.layout_product_list_item, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        with(holder.itemView) {
            val product = items[position]

            lbl_product_name.text = product.name
            lbl_product_description.text = product.description
            btn_add_product.setOnClickListener {
                onAddProductClicked?.invoke(product)
            }

            val numberFormat = NumberFormat.getCurrencyInstance()
            numberFormat.currency = Currency.getInstance("MXN")
            numberFormat.maximumFractionDigits = 0
            lbl_product_price.text = numberFormat.format(product.price)
        }
    }

    fun updateItems(items: List<Product>) {
        this.items = items.toMutableList()
        notifyDataSetChanged()
    }

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}