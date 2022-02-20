package com.perac.marvelheroes.ui.herolist

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.distinctUntilChanged
import com.perac.marvelheroes.base.BaseViewModel
import com.perac.marvelheroes.network.models.CharacterDataWrapper
import com.perac.marvelheroes.network.repository.MarvelHeroesRepository
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers

class MarvelHeroesListViewModel(
    private val marvelHeroesRepository: MarvelHeroesRepository
) : BaseViewModel() {

    private val _marvelHeroesListLiveData = MediatorLiveData<CharacterDataWrapper>()
    val marvelHeroesListLiveData = _marvelHeroesListLiveData.distinctUntilChanged()

    init {
        println("perac: init view model")
        fetchHeroesList()
    }

    private fun updateLiveData(data: CharacterDataWrapper) {
        _marvelHeroesListLiveData.value = data
    }

    private fun fetchHeroesList() =
        marvelHeroesRepository.fetchHeroesList()
//            .doOnSubscribe {
//                compositeDisposable.add(it)
//            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                _marvelHeroesListLiveData.value = it
            }, {

            })
}