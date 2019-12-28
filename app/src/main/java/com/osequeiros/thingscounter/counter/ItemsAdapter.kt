package com.osequeiros.thingscounter.counter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.osequeiros.thingscounter.R
import kotlinx.android.synthetic.main.card_item.view.*

class ItemsAdapter(private var items: List<ItemModel> = emptyList()) :
    RecyclerView.Adapter<ItemsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.card_item, parent, false)
        )
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    fun updateItems(items: List<ItemModel>) {
        this.items = items
        notifyDataSetChanged()
    }

    class ViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: ItemModel) = with(view) {
            textItemTitle.text = item.title
            textItemQuantity.text = item.subtitle
        }
    }
}