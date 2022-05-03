package com.hexad.library.service

import com.hexad.library.model.dto.LibraryContentDto

interface LibraryContentService {
    fun putBooksIntoLibrary(request: LibraryContentDto): LibraryContentDto
    fun getBooks(): LibraryContentDto
}