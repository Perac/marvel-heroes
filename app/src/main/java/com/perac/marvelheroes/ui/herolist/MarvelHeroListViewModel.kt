package com.perac.marvelheroes.ui.herolist

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.distinctUntilChanged
import com.perac.marvelheroes.base.BaseViewModel
import com.perac.marvelheroes.network.models.Character
import com.perac.marvelheroes.network.repository.MarvelHeroesRepository
import io.reactivex.rxjava3.core.Observable

class MarvelHeroListViewModel(
    private val marvelHeroesRepository: MarvelHeroesRepository
) : BaseViewModel<List<Character>>() {

    override val _liveData = MediatorLiveData<List<Character>>()
    override val liveData = _liveData.distinctUntilChanged()

    override fun fetchData(): Observable<List<Character>> = marvelHeroesRepository.fetchHeroesList()
        .map { it.data.results }

    init {
        updateData()
    }
}