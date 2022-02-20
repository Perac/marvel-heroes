package com.perac.marvelheroes.di

import com.perac.marvelheroes.ui.herodetails.MarvelHeroDetailsFragmentArgs
import com.perac.marvelheroes.ui.herodetails.MarvelHeroDetailsViewModel
import com.perac.marvelheroes.ui.herolist.MarvelHeroesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fragmentModule = module {

    viewModel { MarvelHeroesListViewModel(marvelHeroesRepository = get()) }

    viewModel { (navArgs: MarvelHeroDetailsFragmentArgs) ->
        MarvelHeroDetailsViewModel(
            marvelHeroesRepository = get(),
            navArgs.heroId
        )
    }
}