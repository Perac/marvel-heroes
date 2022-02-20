package com.perac.marvelheroes.network.models

data class CharacterDataWrapper(
    val data: CharacterDataContainer
)

data class CharacterDataContainer(
    val results: List<Character>,
    val total: Int,
    val count: Int
)

data class Character(
    val id: Int,
    val name: String,
    val description: String,
    val comics: List<ComicList>
)