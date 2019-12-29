package com.osequeiros.thingscounter.rx

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter {

    private val disposables = CompositeDisposable()

    fun launch(function: () -> Disposable) {
        disposables.add(function.invoke())
    }

    fun stop() {
        disposables.clear()
    }
}