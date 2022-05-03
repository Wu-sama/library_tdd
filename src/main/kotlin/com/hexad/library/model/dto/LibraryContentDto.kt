package com.hexad.library.model.dto

data class LibraryContentDto(
    val books: Map<String, Int> = emptyMap()
)