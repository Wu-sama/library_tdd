package com.wolt.app.test.model.dto

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class LibraryTest {

    @Test
    fun addBook() {
        val addedBooks =  mapOf("book1" to 1)
        val library = Library(books = mutableMapOf("book1" to 0))

        library.addBooks(addedBooks)

        assertEquals(1, library.books.size)
        assertEquals(1, library.books["book1"])
    }

    @Test
    fun addAnotherExampleOfBook(){
        val addedBooks =  mapOf("book1" to 1)
        val library = Library(books = mutableMapOf("book1" to 2))

        library.addBooks(addedBooks)

        assertEquals(1, library.books.size)
        assertEquals(3, library.books["book1"])
    }

    @Test
    fun addANewBook(){
        val addedBooks =  mapOf("book1" to 1)
        val library = Library()

        library.addBooks(addedBooks)

        assertEquals(1, library.books.size)
        assertEquals(1, library.books["book1"])
    }
}