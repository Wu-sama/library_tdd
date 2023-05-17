package com.library.service

import com.library.model.Book
import com.library.model.Library
import com.library.model.dto.BookDto
import com.library.model.dto.LibraryContentDto
import com.library.model.dto.RecordDto
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
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

    @Test
    fun add_Book(){
        val bookDto = BookDto(name = "name", author = "author")
        val result = libraryContentService.addBook(bookDto)
        assertEquals(result, bookDto)
        assertTrue(Library.getBookList().keys.contains(Book(bookDto)))
    }

    private fun getLibraryContentDto() =
        LibraryContentDto(listOf(RecordDto(bookDto = BookDto(name = "book1", author = "author"), 1)))
}