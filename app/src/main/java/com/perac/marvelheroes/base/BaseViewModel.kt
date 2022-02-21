package com.perac.marvelheroes.base

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable

/**
 * Base ViewModel class which handles data fetching and updating live data for views.
 */
abstract class BaseViewModel<DATA : Any> : ViewModel() {

    protected abstract val _liveData: MediatorLiveData<DATA>
    abstract val liveData: LiveData<DATA>

    private val compositeDisposable = CompositeDisposable()

    protected fun updateData() {
        fetchData()
            .observeOn(AndroidSchedulers.mainThread())
            .doOnSubscribe {
                compositeDisposable.add(it)
            }
            .subscribe({
                _liveData.value = it
            }, {
                Log.e(javaClass.name, it.message, it)
            })
    }

    protected abstract fun fetchData(): Observable<DATA>

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}