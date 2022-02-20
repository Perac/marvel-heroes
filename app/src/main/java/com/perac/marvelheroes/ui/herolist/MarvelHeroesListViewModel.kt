package com.perac.marvelheroes.ui.herolist

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.distinctUntilChanged
import com.perac.marvelheroes.base.BaseViewModel
import com.perac.marvelheroes.network.models.CharacterDataWrapper
import com.perac.marvelheroes.network.repository.MarvelHeroesRepository

class MarvelHeroesListViewModel(
    private val marvelHeroesRepository: MarvelHeroesRepository
) : BaseViewModel<CharacterDataWrapper>() {

    override val _liveData = MediatorLiveData<CharacterDataWrapper>()
    override val liveData = _liveData.distinctUntilChanged()

    override fun fetchData() = marvelHeroesRepository.fetchHeroesList()

    init {
        updateData()
    }
}