package com.osequeiros.thingscounter.mvibase

import io.reactivex.Completable
import io.reactivex.Observable
import io.reactivex.Single

fun <T> Observable<T>.sideEffect(sideEffect: (T) -> Completable): Observable<T> {
    return flatMap { value ->
        Observable.create<T> { emitter ->
            sideEffect.invoke(value).subscribe(
                {
                    emitter.onNext(value)
                    emitter.onComplete()
                },
                { error ->
                    error.printStackTrace()
                }
            )
        }
    }
}

fun <T> Completable.sideEffect(sideEffect: Completable): Completable {
    return andThen(sideEffect)
}

fun <T> Single<T>.sideEffect(sideEffect: (T) -> Completable): Observable<T> {
    return flatMapObservable { value ->
        Observable.create<T> { emitter ->
            sideEffect.invoke(value).subscribe(
                {
                    emitter.onNext(value)
                    emitter.onComplete()
                },
                { error ->
                    error.printStackTrace()
                }
            )
        }
    }
}