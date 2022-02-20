package com.perac.marvelheroes.di

import com.perac.marvelheroes.ui.herolist.MarvelHeroesListViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val fragmentModule = module {

    viewModel { MarvelHeroesListViewModel(marvelHeroesRepository = get()) }
}