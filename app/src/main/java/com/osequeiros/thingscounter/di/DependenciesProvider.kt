package com.osequeiros.thingscounter.di

import android.content.Context
import com.osequeiros.thingscounter.data.*
import com.osequeiros.thingscounter.data.room.ThingsCounterDB
import com.osequeiros.thingscounter.domain.usecases.*
import com.osequeiros.thingscounter.presentation.ItemsProcessorHolder
import com.osequeiros.thingscounter.presentation.ItemsViewModel
import com.osequeiros.thingscounter.presentation.model.UiItemMapper
import com.osequeiros.thingscounter.rx.ScheduleProvider

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
    private val deleteUseCase = DeleteItemUseCase(itemRepository)
    private val createUseCase = CreateItemUseCase(itemRepository)

    private val itemModelMapper = UiItemMapper()

    private fun instanceProcessorHolder() = ItemsProcessorHolder(
        saveItemUseCase = saveItemUseCase,
        increaseItemQuantityUseCase = increaseUseCase,
        decreaseItemQuantityUseCase = decreaseUseCase,
        getItemsUseCase = getItemsUseCase,
        deleteItemUseCase = deleteUseCase,
        createUseCase = createUseCase,
        schedulerProvider = ScheduleProvider()
    )

    fun instanceItemsViewModel() = ItemsViewModel(
        actionProcessorHolder = instanceProcessorHolder(),
        mapper = itemModelMapper
    )
}