package com.osequeiros.thingscounter.presentation

import androidx.lifecycle.ViewModel
import com.osequeiros.thingscounter.mvibase.MviViewModel
import com.osequeiros.thingscounter.presentation.ItemsAction.*
import com.osequeiros.thingscounter.presentation.ItemsIntent.*
import com.osequeiros.thingscounter.presentation.ItemsResult.*
import com.osequeiros.thingscounter.presentation.model.UiItem
import com.osequeiros.thingscounter.presentation.model.UiItemMapper
import com.osequeiros.thingscounter.util.replaceAll
import io.reactivex.Observable
import io.reactivex.functions.BiFunction
import io.reactivex.subjects.PublishSubject

class ItemsViewModel(
    private val actionProcessorHolder: ItemsProcessorHolder,
    private val mapper: UiItemMapper
) :
    ViewModel(),
    MviViewModel<ItemsIntent, ItemsViewState> {

    private val intentsSubject: PublishSubject<ItemsIntent> = PublishSubject.create()

    private val statesObservable: Observable<ItemsViewState> = compose()

    override fun processIntents(intents: Observable<ItemsIntent>) {
        intents.subscribe(intentsSubject)
    }

    override fun states(): Observable<ItemsViewState> = statesObservable

    private fun compose(): Observable<ItemsViewState> {
        return intentsSubject
            .map { intent -> intent.toAction() }
            .compose(actionProcessorHolder.actionProcessor)
            .scan(ItemsViewState.default(), reducer())
            .distinctUntilChanged()
            .replay(1)
            .autoConnect()
    }

    private fun ItemsIntent.toAction(): ItemsAction {
        return when (this) {
            LoadAllItemsIntent      -> LoadAllItemsAction
            is NameItemIntent       -> NameItemAction(name)
            is CreateItemIntent     -> CreateItemAction(mapper.map(item))
            is IncreaseCountIntent  -> IncreaseCountAction(mapper.map(item))
            is DecreaseCountIntent  -> DecreaseCountAction(mapper.map(item))
            is DeleteItemIntent     -> DeleteItemAction(mapper.map(item))
        }
    }

    private fun reducer(): BiFunction<ItemsViewState, ItemsResult, ItemsViewState> =
        BiFunction { previousState: ItemsViewState, result: ItemsResult ->
            when (result) {
                is LoadAllItemsResult   -> reducerLoadAll(previousState, result)
                is NameItemResult       -> reducerName(previousState, result)
                is CreateItemResult     -> reducerCreate(previousState, result)
                is IncreaseCountResult  -> reducerIncrease(previousState, result)
                is DecreaseCountResult  -> reducerDecrease(previousState, result)
                is DeleteItemResult     -> reducerDelete(previousState, result)
            }
        }

    private fun reducerLoadAll(previousState: ItemsViewState, result: LoadAllItemsResult): ItemsViewState =
        when (result) {
            is LoadAllItemsResult.Success ->
                previousState.copy(isProcessing = false, items = mapper.map(result.items), error = null)
            is LoadAllItemsResult.Failure ->
                previousState.copy(isProcessing = false, error = result.error)
            is LoadAllItemsResult.Processing ->
                previousState.copy(isProcessing = true, error = null)
        }

    private fun reducerName(previousState: ItemsViewState, result: NameItemResult): ItemsViewState =
        when (result) {
            is NameItemResult.Success ->
                previousState.copy(isProcessing = false, error = null)
            is NameItemResult.Failure ->
                previousState.copy(isProcessing = false, error = result.error)
            is NameItemResult.Processing ->
                previousState.copy(isProcessing = true, error = null)
        }

    private fun reducerCreate(previousState: ItemsViewState, result: CreateItemResult): ItemsViewState =
        when (result) {
            is CreateItemResult.Success ->
                previousState.copy(isProcessing = false, items = previousState.items.addItem(mapper.map(result.item)), error = null)
            is CreateItemResult.Failure ->
                previousState.copy(isProcessing = false, error = result.error)
            is CreateItemResult.Processing ->
                previousState.copy(isProcessing = true, error = null)
        }

    private fun reducerDelete(previousState: ItemsViewState, result: DeleteItemResult): ItemsViewState =
        when (result) {
            is DeleteItemResult.Success ->
                previousState.copy(isProcessing = false, items = previousState.items.deleteItem(mapper.map(result.item)), error = null)
            is DeleteItemResult.Failure ->
                previousState.copy(isProcessing = false, error = result.error)
            is DeleteItemResult.Processing ->
                previousState.copy(isProcessing = true, error = null)
        }

    private fun reducerIncrease(previousState: ItemsViewState, result: IncreaseCountResult): ItemsViewState =
        when (result) {
            is IncreaseCountResult.Success ->
                previousState.copy(isProcessing = false, items = previousState.items.replaceItem(mapper.map(result.item)), error = null)
            is IncreaseCountResult.Failure ->
                previousState.copy(isProcessing = false, error = result.error)
            is IncreaseCountResult.Processing ->
                previousState.copy(isProcessing = true, error = null)
        }

    private fun reducerDecrease(previousState: ItemsViewState, result: DecreaseCountResult): ItemsViewState =
        when (result) {
            is DecreaseCountResult.Success ->
                previousState.copy(isProcessing = false, items = previousState.items.replaceItem(mapper.map(result.item)), error = null)
            is DecreaseCountResult.Failure ->
                previousState.copy(isProcessing = false, error = result.error)
            is DecreaseCountResult.Processing ->
                previousState.copy(isProcessing = true, error = null)
        }

    private fun List<UiItem>.deleteItem(item: UiItem): List<UiItem> {
        return filter { it.code != item.code }
    }

    private fun List<UiItem>.addItem(item: UiItem): List<UiItem> {
        return toMutableList().also { it.add(item) }
    }

    private fun List<UiItem>.replaceItem(item: UiItem): List<UiItem> {
        return replaceAll(item) { it.code == item.code }
    }
}