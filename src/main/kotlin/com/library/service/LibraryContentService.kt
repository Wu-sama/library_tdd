package com.library.service

import com.library.model.dto.BookDto
import com.library.model.dto.LibraryContentDto

interface LibraryContentService {
    fun putBooksIntoLibrary(request: LibraryContentDto): LibraryContentDto
    fun getBooks(): LibraryContentDto
    fun addBook(book: BookDto): BookDto
}