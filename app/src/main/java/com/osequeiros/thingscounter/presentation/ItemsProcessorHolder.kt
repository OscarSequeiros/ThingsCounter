package com.osequeiros.thingscounter.presentation

import com.osequeiros.thingscounter.domain.usecases.*
import com.osequeiros.thingscounter.presentation.ItemsAction.*
import com.osequeiros.thingscounter.presentation.ItemsResult.*
import com.osequeiros.thingscounter.rx.BaseSchedulerProvider
import com.osequeiros.thingscounter.mvibase.sideEffect

import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class ItemsProcessorHolder(
    private val createUseCase: CreateItemUseCase,
    private val saveItemUseCase: SaveItemUseCase,
    private val decreaseItemQuantityUseCase: DecreaseItemQuantityUseCase,
    private val increaseItemQuantityUseCase: IncreaseItemQuantityUseCase,
    private val getItemsUseCase: GetItemsUseCase,
    private val deleteItemUseCase: DeleteItemUseCase,
    private val schedulerProvider: BaseSchedulerProvider
) {

    private val loadAllProcessor =
        ObservableTransformer<LoadAllItemsAction, LoadAllItemsResult> { actions ->
            actions
                .flatMap {
                    getItemsUseCase.execute()
                        .toObservable()
                        .map(LoadAllItemsResult::Success)
                        .cast(LoadAllItemsResult::class.java)
                        .onErrorReturn(LoadAllItemsResult::Failure)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .startWith(LoadAllItemsResult.Processing)
                }
        }

    private val nameProcessor =
        ObservableTransformer<NameItemAction, NameItemResult> { actions ->
            actions
                .map { action -> NameItemResult.Success(action.name) }
                .cast(NameItemResult::class.java)
                .subscribeOn(schedulerProvider.io())
                .observeOn(schedulerProvider.ui())
                .onErrorReturn(NameItemResult::Failure)
                .startWith(NameItemResult.Processing)
        }

    private val createProcessor =
        ObservableTransformer<CreateItemAction, CreateItemResult> { actions ->
            actions
                .flatMap { action ->
                    createUseCase.execute(action.item)
                        .toObservable()
                        .map(CreateItemResult::Success)
                        .cast(CreateItemResult::class.java)
                        .onErrorReturn(CreateItemResult::Failure)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .startWith(CreateItemResult.Processing)
                }
        }

    private val increaseProcessor =
        ObservableTransformer<IncreaseCountAction, IncreaseCountResult> { actions ->
            actions
                .flatMap { action ->
                    increaseItemQuantityUseCase.execute(action.item)
                        .sideEffect { item -> saveItemUseCase.execute(item) }
                        .map(IncreaseCountResult::Success)
                        .cast(IncreaseCountResult::class.java)
                        .onErrorReturn(IncreaseCountResult::Failure)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .startWith(IncreaseCountResult.Processing)
                }
        }

    private val decreaseProcessor =
        ObservableTransformer<DecreaseCountAction, DecreaseCountResult> { actions ->
            actions
                .flatMap { action ->
                    decreaseItemQuantityUseCase.execute(action.item)
                        .sideEffect { item -> saveItemUseCase.execute(item) }
                        .map(DecreaseCountResult::Success)
                        .cast(DecreaseCountResult::class.java)
                        .onErrorReturn(DecreaseCountResult::Failure)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .startWith(DecreaseCountResult.Processing)
                }
        }

    private val deleteProcessor =
        ObservableTransformer<DeleteItemAction, DeleteItemResult> { actions ->
            actions
                .flatMap { action ->
                    deleteItemUseCase.execute(action.item)
                        .andThen(Observable.just(DeleteItemResult.Success(action.item)))
                        .cast(DeleteItemResult::class.java)
                        .onErrorReturn(DeleteItemResult::Failure)
                        .subscribeOn(schedulerProvider.io())
                        .observeOn(schedulerProvider.ui())
                        .startWith(DeleteItemResult.Processing)
                }
        }

    internal val actionProcessor =
        ObservableTransformer<ItemsAction, ItemsResult> { actions ->
            actions.publish { shared ->
                Observable.merge(
                    shared.ofType(NameItemAction::class.java).compose(nameProcessor),
                    shared.ofType(CreateItemAction::class.java).compose(createProcessor))
                    .mergeWith(shared.ofType(LoadAllItemsAction::class.java).compose(loadAllProcessor))
                    .mergeWith(shared.ofType(IncreaseCountAction::class.java).compose(increaseProcessor))
                    .mergeWith(shared.ofType(DecreaseCountAction::class.java).compose(decreaseProcessor))
                    .mergeWith(shared.ofType(DeleteItemAction::class.java).compose(deleteProcessor))
                    .mergeWith(
                        shared.filter { action ->
                            action !is LoadAllItemsAction
                                    && action !is NameItemAction
                                    && action !is CreateItemAction
                                    && action !is IncreaseCountAction
                                    && action !is DecreaseCountAction
                                    && action !is DeleteItemAction
                        }.flatMap { action ->
                            Observable.error<ItemsResult> {
                                IllegalArgumentException("unknown action type :$action")
                            }
                        }
                    )
            }
        }

}