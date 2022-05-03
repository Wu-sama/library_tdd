package com.hexad.library.service

import com.hexad.library.model.Library
import com.hexad.library.model.dto.LibraryContentDto
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach

internal class LibraryContentServiceImplTest {
    private val libraryContentService: LibraryContentService = LibraryContentServiceImpl()

    @BeforeEach
    private fun clearContext(){
        Library.clear()
    }

    @Test
    fun putBooksIntoLibrary() {
        val libraryContentDto = LibraryContentDto(books = mutableMapOf("book1" to 1))
        val result = libraryContentService.putBooksIntoLibrary(libraryContentDto)
        assertEquals(1, result.books.size)
    }

    @Test
    fun getBooks() {
        val libraryContentDto = LibraryContentDto(books = mutableMapOf("book1" to 1))
        libraryContentService.putBooksIntoLibrary(libraryContentDto)
        val result = libraryContentService.getBooks()
        assertEquals(1, result.books.size)
    }
}