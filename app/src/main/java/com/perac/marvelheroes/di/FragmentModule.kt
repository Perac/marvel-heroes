package com.perac.marvelheroes.di

import com.perac.marvelheroes.ui.herodetails.MarvelHeroDetailsFragmentArgs
import com.perac.marvelheroes.ui.herodetails.MarvelHeroDetailsViewModel
import com.perac.marvelheroes.ui.herolist.MarvelHeroListViewModel
import com.perac.marvelheroes.ui.search.MarvelHeroSearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

/**
 * Koin module for fragment dependencies.
 */
val fragmentModule = module {

    viewModel { MarvelHeroListViewModel(marvelHeroesRepository = get()) }

    viewModel { (navArgs: MarvelHeroDetailsFragmentArgs) ->
        MarvelHeroDetailsViewModel(
            marvelHeroesRepository = get(),
            navArgs.heroId
        )
    }

    viewModel { MarvelHeroSearchViewModel(marvelHeroesRepository = get()) }
}