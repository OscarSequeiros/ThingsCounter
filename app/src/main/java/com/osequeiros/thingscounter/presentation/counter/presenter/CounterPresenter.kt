package com.osequeiros.thingscounter.presentation.counter.presenter

import com.osequeiros.thingscounter.domain.exceptions.ForbiddenDecreaseException
import com.osequeiros.thingscounter.domain.usecases.DecreaseItemQuantityUseCase
import com.osequeiros.thingscounter.domain.usecases.GetItemsUseCase
import com.osequeiros.thingscounter.domain.usecases.IncreaseItemQuantityUseCase
import com.osequeiros.thingscounter.domain.usecases.SaveItemUseCase
import com.osequeiros.thingscounter.presentation.counter.CounterContract
import com.osequeiros.thingscounter.presentation.counter.model.ItemModel
import com.osequeiros.thingscounter.presentation.counter.model.ItemModelMapper
import com.osequeiros.thingscounter.rx.BasePresenter
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CounterPresenter(
    private val saveUseCase: SaveItemUseCase,
    private val increaseUseCase: IncreaseItemQuantityUseCase,
    private val decreaseUseCase: DecreaseItemQuantityUseCase,
    private val getItemsUseCase: GetItemsUseCase,
    private val view: CounterContract.View,
    private val mapper: ItemModelMapper
) : CounterContract.Presenter, BasePresenter() {

    override fun createItem(itemModel: ItemModel) {
        launch {
            saveUseCase.execute(mapper.map(itemModel))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { getItems() },
                    { manageError(it) })
        }
    }

    override fun increaseItem(itemModel: ItemModel) {
        launch {
            increaseUseCase.execute(mapper.map(itemModel))
                .flatMapCompletable { saveUseCase.execute(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { getItems() },
                    { manageError(it) })
        }
    }

    override fun decreaseItem(itemModel: ItemModel) {
        launch {
            decreaseUseCase.execute(mapper.map(itemModel))
                .flatMapCompletable { saveUseCase.execute(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { getItems() },
                    { manageError(it) })
        }
    }

    override fun getItems() {
        launch {
            getItemsUseCase.execute()
                .map { mapper.map(it) }
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                    { manageResponse(it) },
                    { manageError(it) })
        }
    }

    private fun manageResponse(items: List<ItemModel>) {
        if (items.isEmpty()) {
            view.showEmptyList()
        } else {
            view.showItemList(items)
        }
        val total = items.sumBy { it.quantity }
        view.showTotal("Total items $total")
    }

    private fun manageError(exception: Throwable) {
        if (exception is ForbiddenDecreaseException) {
            view.prohibitDecrease()
        }
    }
}