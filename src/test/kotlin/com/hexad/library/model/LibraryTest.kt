package com.hexad.library.model

import com.hexad.library.exeption.BookNotFoundException
import com.hexad.library.exeption.NotEnoughBookInTheLibraryException
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertThrows
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

    @Test
    fun borrowBook(){
        Library.addBooks(books = mutableMapOf("book1" to 2))

        Library.borrowBook("book1")

        assertEquals(1, Library.getBookList().size)
        assertEquals(1, Library.getBookList()["book1"])
    }

    @Test
    fun borrowBookNotFound(){
        Library.addBooks(books = mutableMapOf("book1" to 2))

        assertThrows(
            BookNotFoundException::class.java,
            { Library.borrowBook("book2") },
            "Expected doThing() to throw, but it didn't"
        )

        assertEquals(1, Library.getBookList().size)
        assertEquals(2, Library.getBookList()["book1"])
    }

    @Test
    fun borrowBookNotEnoughCopies(){
        Library.addBooks(books = mutableMapOf("book1" to 0))

        assertThrows(
            NotEnoughBookInTheLibraryException::class.java,
            { Library.borrowBook("book1") },
            "Expected doThing() to throw, but it didn't"
        )

        assertEquals(0, Library.getBookList().size)
    }
}