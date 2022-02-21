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
    val comics: ComicList,
    val thumbnail: Image
) {
    fun getImageUrl() = if (thumbnail.path.contains("http:")) {
        "${thumbnail.path.replace("http:", "https:")}.${thumbnail.extension}"
    } else {
        "${thumbnail.path}.${thumbnail.extension}"
    }
}

data class Image(
    val path: String,
    val extension: String
)