package com.perac.marvelheroes.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Single
import io.reactivex.rxjava3.disposables.CompositeDisposable

abstract class BaseViewModel<DATA : Any> : ViewModel() {

    protected abstract val _liveData: MediatorLiveData<DATA>
    abstract val liveData: LiveData<DATA>

    protected val compositeDisposable = CompositeDisposable()

    protected fun updateData() {
        fetchData()
            .doOnSubscribe {
                compositeDisposable.add(it)
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _liveData.value = it
            }, {
                Log.e(javaClass.name, it.message, it)
            })
    }

    protected abstract fun fetchData(): Single<DATA>

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}