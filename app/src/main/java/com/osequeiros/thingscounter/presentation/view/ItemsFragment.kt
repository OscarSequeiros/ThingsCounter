package com.osequeiros.thingscounter.presentation.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.osequeiros.thingscounter.R
import com.osequeiros.thingscounter.domain.exceptions.ForbiddenDecreaseException
import com.osequeiros.thingscounter.domain.exceptions.ItemNameExpectedException
import com.osequeiros.thingscounter.presentation.*
import com.osequeiros.thingscounter.presentation.ItemsIntent.*
import com.osequeiros.thingscounter.presentation.model.UiItem
import com.osequeiros.thingscounter.util.visible
import io.reactivex.Observable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.subjects.PublishSubject
import kotlinx.android.synthetic.main.fragment_items.*

class ItemsFragment : Fragment(), NewItemCallback, ActionsItemCallback {

    private val namePublisher = PublishSubject.create<NameItemIntent>()
    private val savePublisher = PublishSubject.create<CreateItemIntent>()
    private val increasePublisher = PublishSubject.create<IncreaseCountIntent>()
    private val decreasePublisher = PublishSubject.create<DecreaseCountIntent>()
    private val deletePublisher = PublishSubject.create<DeleteItemIntent>()


    private var adapter = ItemsAdapter(callback = this)

    private val disposables = CompositeDisposable()

    private val viewModel: ItemsViewModel by lazy(LazyThreadSafetyMode.NONE) {
        ViewModelProvider(this, ItemsViewModelFactory(context ?: throw IllegalArgumentException()))
            .get(ItemsViewModel::class.java)
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
    }

    override fun onStart() {
        bind()
        super.onStart()
    }

    override fun onStop() {
        super.onStop()
        disposables.clear()
    }

    private fun bind() {
        disposables.add(viewModel.states().subscribe(this::render))
        viewModel.processIntents(intents())
    }

    private fun render(state: ItemsViewState) {
        manageItems(state.items)
        manageExceptions(state.error)
    }

    private fun manageItems(items: List<UiItem>) {
        constraintEmptyState.visible(items.isEmpty())
        showItemList(items)
        showTotal("Total items: ${items.sumBy { it.quantity }}")
    }

    private fun manageExceptions(throwable: Throwable?) {
        when (throwable) {
            is ForbiddenDecreaseException -> prohibitDecrease()
            is ItemNameExpectedException -> showNameRequiredMessage()
        }
    }

    private fun intents(): Observable<ItemsIntent> {
        return Observable.merge(
            loadIntent(),
            namePublisher
        )
            .mergeWith(increasePublisher)
            .mergeWith(decreasePublisher)
            .mergeWith(savePublisher)
            .mergeWith(deletePublisher)
    }

    private fun loadIntent() = Observable.just(LoadAllItemsIntent)

    private fun setUpRecycler() {
        recyclerItems.layoutManager = LinearLayoutManager(context)
        recyclerItems.adapter = adapter
    }

    private fun setUpActions() {
        fabAddItem.setOnClickListener {
            activity?.let {
                NewItemDialog.instance(this).show(it.supportFragmentManager, null)
            }
        }
    }

    private fun showItemList(items: List<UiItem>) {
        adapter.updateItems(items)
    }

    private fun prohibitDecrease() {
        activity?.vibrate(150)
    }

    private fun showNameRequiredMessage() {
        context?.toast(getString(R.string.item_name_required))
    }

    private fun showTotal(message: String) {
        textToolbar.text = message
    }

    override fun addItem(item: UiItem) {
        savePublisher.onNext(CreateItemIntent(item))
    }

    override fun increase(item: UiItem) {
        increasePublisher.onNext(IncreaseCountIntent(item))
    }

    override fun decrease(item: UiItem) {
        decreasePublisher.onNext(DecreaseCountIntent(item))
    }

    override fun delete(item: UiItem) {
        deletePublisher.onNext(DeleteItemIntent(item))
    }
}