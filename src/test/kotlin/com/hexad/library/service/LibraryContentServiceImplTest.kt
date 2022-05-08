package com.hexad.library.service

import com.hexad.library.model.Library
import com.hexad.library.model.dto.BookDto
import com.hexad.library.model.dto.LibraryContentDto
import com.hexad.library.model.dto.RecordDto
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach

internal class LibraryContentServiceImplTest {
    private val libraryContentService: LibraryContentService = LibraryContentServiceImpl()

    @BeforeEach
    private fun clearContext() {
        Library.clean()
    }

    @Test
    fun putBooksIntoLibrary() {
        val result = libraryContentService.putBooksIntoLibrary(getLibraryContentDto())
        assertEquals(1, result.books.size)
    }

    @Test
    fun getBooks() {
        libraryContentService.putBooksIntoLibrary(getLibraryContentDto())
        val result = libraryContentService.getBooks()
        assertEquals(1, result.books.size)
    }

    private fun getLibraryContentDto() =
        LibraryContentDto(listOf(RecordDto(bookDto = BookDto(name = "book1", author = "author"), 1)))
}