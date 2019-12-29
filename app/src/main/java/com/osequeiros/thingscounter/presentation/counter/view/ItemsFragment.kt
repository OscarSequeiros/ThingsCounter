package com.osequeiros.thingscounter.presentation.counter.view

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.osequeiros.thingscounter.R
import com.osequeiros.thingscounter.di.DependenciesProvider
import com.osequeiros.thingscounter.presentation.add.NewItemBottomSheetDialog
import com.osequeiros.thingscounter.presentation.counter.CounterContract
import com.osequeiros.thingscounter.presentation.counter.model.ItemModel
import kotlinx.android.synthetic.main.fragment_items.*

class ItemsFragment : Fragment(), CounterContract.View, NewItemCallback, ActionsItemCallback {

    private var adapter = ItemsAdapter(callback = this)

    private val provider by lazy {
        DependenciesProvider(context ?: throw IllegalArgumentException())
    }

    private val presenter by lazy {
        provider.instanceCounterPresenter(this)
    }

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
        setUpActions()

        presenter.getItems()
    }

    private fun setUpRecycler() {
        recyclerItems.layoutManager = LinearLayoutManager(context)
        recyclerItems.adapter = adapter
    }

    private fun setUpActions() {
        fabAddItem.setOnClickListener {
            val bottomSheet = NewItemBottomSheetDialog.instance(this)
            bottomSheet.show(activity!!.supportFragmentManager, null)
        }
    }

    override fun showEmptyList() {
        Toast.makeText(context, "Lista vacía", Toast.LENGTH_SHORT).show()
    }

    override fun showItemList(items: List<ItemModel>) {
        adapter.updateItems(items)
    }

    override fun prohibitDecrease() {
        val vibrator: Vibrator? = activity?.getSystemService(Context.VIBRATOR_SERVICE) as? Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator?.vibrate(VibrationEffect.createOneShot(150, VibrationEffect.DEFAULT_AMPLITUDE))
        } else {
            vibrator?.vibrate(150)
        }
    }

    override fun showTotal(message: String) {
        textToolbar.text = message
    }

    override fun addItem(model: ItemModel) {
        presenter.createItem(model)
    }

    override fun increase(item: ItemModel) {
        presenter.increaseItem(item)
    }

    override fun decrease(item: ItemModel) {
        presenter.decreaseItem(item)
    }
}