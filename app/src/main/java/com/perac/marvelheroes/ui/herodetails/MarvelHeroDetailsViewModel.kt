package com.perac.marvelheroes.ui.herodetails

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.distinctUntilChanged
import com.perac.marvelheroes.base.BaseViewModel
import com.perac.marvelheroes.network.models.Character
import com.perac.marvelheroes.network.repository.MarvelHeroesRepository
import io.reactivex.rxjava3.core.Observable

/**
 * ViewModel used for fetching hero details and displaying that info in a view.
 */
class MarvelHeroDetailsViewModel(
    private val marvelHeroesRepository: MarvelHeroesRepository,
    private val heroId: String
) : BaseViewModel<Character>() {

    override val _liveData = MediatorLiveData<Character>()
    override val liveData = _liveData.distinctUntilChanged()

    override fun fetchData(): Observable<Character> =
        marvelHeroesRepository.fetchHeroDetails(heroId)
            .map { it.data.results.first() }

    init {
        updateData()
    }
}