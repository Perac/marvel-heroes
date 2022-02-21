package com.perac.marvelheroes.ui.herolist

import androidx.lifecycle.MediatorLiveData
import com.perac.marvelheroes.base.BaseViewModel
import com.perac.marvelheroes.network.models.Character
import com.perac.marvelheroes.network.repository.MarvelHeroesRepository
import io.reactivex.rxjava3.core.Observable

/**
 * ViewModel used to fetch Marvel heroes list data and update live data for UI.
 */
class MarvelHeroListViewModel(
    private val marvelHeroesRepository: MarvelHeroesRepository
) : BaseViewModel<List<Character>>() {

    override val _liveData = MediatorLiveData<List<Character>>()
    override val liveData = _liveData

    private var initialOffset = 0

    override fun fetchData(): Observable<List<Character>> = marvelHeroesRepository.fetchHeroesList()
        .map { it.data.results }

    init {
        updateData()
    }

    fun loadMore() {
        initialOffset += PAGE_SIZE
        marvelHeroesRepository.fetchHeroesList(offset = initialOffset, limit = PAGE_SIZE)
            .map { it.data.results }
            .subscribe({
                val currentValue = _liveData.value ?: listOf()
                _liveData.postValue(currentValue.plus(it))
            }, {

            })
    }

    private companion object {
        private const val PAGE_SIZE = 20
    }
}