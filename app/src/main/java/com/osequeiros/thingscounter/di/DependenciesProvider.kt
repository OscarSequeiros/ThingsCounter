package com.osequeiros.thingscounter.di

import android.content.Context
import com.osequeiros.thingscounter.data.*
import com.osequeiros.thingscounter.data.room.ThingsCounterDB
import com.osequeiros.thingscounter.domain.usecases.DecreaseItemQuantityUseCase
import com.osequeiros.thingscounter.domain.usecases.GetItemsUseCase
import com.osequeiros.thingscounter.domain.usecases.IncreaseItemQuantityUseCase
import com.osequeiros.thingscounter.domain.usecases.SaveItemUseCase
import com.osequeiros.thingscounter.presentation.counter.CounterContract
import com.osequeiros.thingscounter.presentation.counter.model.ItemModelMapper
import com.osequeiros.thingscounter.presentation.counter.presenter.CounterPresenter

class DependenciesProvider(context: Context) {

    private val remoteDataSource = RemoteDataSource()

    private val database = ThingsCounterDB.getInstance(context)
    private val localDataSource = LocalDataSource(database.itemRoomDao())

    private val itemDataMapper = ItemDataMapper()

    private val itemRepository = ItemDataRepository(
        localDataSource = localDataSource,
        remoteDataSource = remoteDataSource,
        mapper = itemDataMapper
    ) as ItemRepository

    private val saveItemUseCase = SaveItemUseCase(itemRepository)
    private val increaseUseCase = IncreaseItemQuantityUseCase()
    private val decreaseUseCase = DecreaseItemQuantityUseCase()
    private val getItemsUseCase = GetItemsUseCase(itemRepository)

    private val itemModelMapper = ItemModelMapper()

    fun instanceCounterPresenter(view: CounterContract.View) = CounterPresenter(
        saveUseCase = saveItemUseCase,
        increaseUseCase = increaseUseCase,
        decreaseUseCase = decreaseUseCase,
        getItemsUseCase = getItemsUseCase,
        mapper = itemModelMapper,
        view = view
    ) as CounterContract.Presenter
}