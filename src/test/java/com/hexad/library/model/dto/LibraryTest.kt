package com.hexad.library.model.dto

import com.hexad.library.model.Library
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class LibraryTest {

    @BeforeEach
    fun cleanData(){
        Library.clear()
    }

    @Test
    fun addBook() {
        Library.addBooks(mutableMapOf("book1" to 1))

        assertEquals(1, Library.getBookList().size)
        assertEquals(1, Library.getBookList()["book1"])
    }

    @Test
    fun addAnotherExampleOfBook(){
        val addedBooks =  mapOf("book1" to 1)
        Library.addBooks(books = mutableMapOf("book1" to 2))

        Library.addBooks(addedBooks)

        assertEquals(1, Library.getBookList().size)
        assertEquals(3, Library.getBookList()["book1"])
    }
}