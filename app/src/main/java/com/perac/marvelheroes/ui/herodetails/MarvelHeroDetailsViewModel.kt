package com.perac.marvelheroes.ui.herodetails

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.distinctUntilChanged
import com.perac.marvelheroes.base.BaseViewModel
import com.perac.marvelheroes.network.models.CharacterDataWrapper
import com.perac.marvelheroes.network.repository.MarvelHeroesRepository
import io.reactivex.rxjava3.core.Observable

/**
 * ViewModel used for fetching hero details and displaying that info in a view.
 */
class MarvelHeroDetailsViewModel(
    private val marvelHeroesRepository: MarvelHeroesRepository,
    private val heroId: String
) : BaseViewModel<CharacterDataWrapper>() {

    override val _liveData = MediatorLiveData<CharacterDataWrapper>()
    override val liveData = _liveData.distinctUntilChanged()

    override fun fetchData(): Observable<CharacterDataWrapper> =
        marvelHeroesRepository.fetchHeroDetails(heroId)

    init {
        updateData()
    }
}