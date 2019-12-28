package com.osequeiros.thingscounter.counter

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.osequeiros.thingscounter.R
import kotlinx.android.synthetic.main.fragment_items.*

class ItemsFragment : Fragment() {

    private var adapter = ItemsAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_items, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setUpRecycler()
        adapter.updateItems(generateItems())
    }

    private fun setUpRecycler() {
        recyclerItems.layoutManager = LinearLayoutManager(context)
        recyclerItems.adapter = adapter
    }

    private fun generateItems(): List<ItemModel> {
        return listOf(
            ItemModel(
                code = "1",
                title = "Manzanas",
                subtitle = "4 items"
            ),
            ItemModel(
                code = "1",
                title = "Peras",
                subtitle = "3 items"
            ),
            ItemModel(
                code = "1",
                title = "Zapallos",
                subtitle = "3 items"
            )
        )
    }
}