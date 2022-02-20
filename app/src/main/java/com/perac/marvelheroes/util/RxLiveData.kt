package com.perac.marvelheroes.util

import androidx.lifecycle.LiveData
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.Disposable

class RxLiveData<T : Any>(private val single: Single<T>) : LiveData<T>() {

    private var disposable: Disposable? = null

    override fun onActive() {
        disposable = single.subscribe({
            postValue(it)
        }, {

        })
    }

    override fun onInactive() {
        disposable?.dispose()
    }

}

fun <T : Any> Single<T>.asLiveData(): LiveData<T> = RxLiveData(this)