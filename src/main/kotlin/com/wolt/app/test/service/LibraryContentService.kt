package com.wolt.app.test.service

import com.wolt.app.test.model.dto.LibraryContentDto

interface LibraryContentService {
    fun putBooksIntoLibrary(request: LibraryContentDto): LibraryContentDto
    fun getBooks(): LibraryContentDto
}