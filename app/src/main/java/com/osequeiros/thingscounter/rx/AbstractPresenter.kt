package com.osequeiros.thingscounter.rx

import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class AbstractPresenter : BasePresenter {

    private val disposables = CompositeDisposable()

    fun launch(function: () -> Disposable) {
        disposables.add(function.invoke())
    }

    override fun stop() {
        disposables.clear()
    }
}