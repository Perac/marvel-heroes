package com.perac.marvelheroes.ui.search

import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.distinctUntilChanged
import com.perac.marvelheroes.base.BaseViewModel
import com.perac.marvelheroes.network.models.Character
import com.perac.marvelheroes.network.repository.MarvelHeroesRepository
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.subjects.PublishSubject
import java.util.concurrent.TimeUnit

/**
 * ViewModel used for searching marvel heroes.
 */
class MarvelHeroSearchViewModel(
    private val marvelHeroesRepository: MarvelHeroesRepository
) : BaseViewModel<List<Character>>() {

    override val _liveData = MediatorLiveData<List<Character>>()
    override val liveData = _liveData.distinctUntilChanged()

    private val searchSubject = PublishSubject.create<String>()

    override fun fetchData() = searchSubject
        .debounce(500, TimeUnit.MILLISECONDS)
        .flatMap {
            if (it.isEmpty()) {
                Observable.just(emptyList())
            } else {
                marvelHeroesRepository.fetchHeroesList(it)
                    .map { characterDataWrapper ->
                        characterDataWrapper.data.results
                    }
            }
        }

    init {
        updateData()
    }

    fun updateSearchTerm(searchTerm: String) {
        searchSubject.onNext(searchTerm)
    }
}