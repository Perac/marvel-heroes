package com.perac.marvelheroes.network.models

data class ComicList(
    val items: List<ComicSummary>
)

data class ComicSummary(
    val name: String
)