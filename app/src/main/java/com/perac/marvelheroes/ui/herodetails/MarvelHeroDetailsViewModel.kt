package com.perac.marvelheroes.ui.herodetails

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.distinctUntilChanged
import com.perac.marvelheroes.base.BaseViewModel
import com.perac.marvelheroes.network.models.CharacterDataWrapper
import com.perac.marvelheroes.network.repository.MarvelHeroesRepository

class MarvelHeroDetailsViewModel(
    private val marvelHeroesRepository: MarvelHeroesRepository,
    private val heroId: String
) : BaseViewModel<CharacterDataWrapper>() {

    override val _liveData = MediatorLiveData<CharacterDataWrapper>()
    override val liveData = _liveData.distinctUntilChanged()

    override fun fetchData() = marvelHeroesRepository.fetchHeroDetails(heroId)

    init {
        updateData()
    }
}