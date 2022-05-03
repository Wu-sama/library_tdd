package com.wolt.app.test.service

import com.wolt.app.test.model.dto.LibraryContentDto
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals

internal class LibraryContentServiceImplTest {
    private val libraryContentService: LibraryContentService = LibraryContentServiceImpl()

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