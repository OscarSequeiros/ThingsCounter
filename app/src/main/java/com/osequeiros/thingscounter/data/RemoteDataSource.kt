package com.osequeiros.thingscounter.data

import com.osequeiros.thingscounter.data.room.entity.ItemRoom
import io.reactivex.Single

class RemoteDataSource {

    fun send(items: List<ItemRoom>): Single<List<ItemRoom>> {
        //TODO: Pending to complete
        return Single.fromCallable { emptyList<ItemRoom>() }
    }
}