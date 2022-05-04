package com.hexad.library.model

import com.hexad.library.exeption.BookNotFoundException
import com.hexad.library.exeption.NotEnoughBookCopiesException
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows

internal class LibraryTest {

    @BeforeEach
    fun cleanData(){
        Library.clean()
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

        assertThrows<BookNotFoundException> { Library.borrowBook("book2") }

        assertEquals(1, Library.getBookList().size)
        assertEquals(2, Library.getBookList()["book1"])
    }

    @Test
    fun borrowBookNotEnoughCopies(){
        Library.addBooks(books = mutableMapOf("book1" to 0))

        assertThrows<NotEnoughBookCopiesException> { Library.borrowBook("book1") }

        assertEquals(0, Library.getBookList().size)
    }

    @Test
    fun bookCanBeBorrowed() {
        val book = "book1"
        Library.addBooks(mutableMapOf(book to 1))
        Library.checkIfBookCanBeBorrowed(book)
    }

    @Test
    fun bookCanNotBeBorrowed() {
        val book = "book1"
        Library.addBooks(mutableMapOf(book to 1))

        assertThrows<BookNotFoundException> { Library.checkIfBookCanBeBorrowed(book + 2) }
    }

    @Test
    fun notEnoughBookForBorrowing() {
        val book = "book1"
        Library.addBooks(mutableMapOf(book to 0))

        assertThrows<NotEnoughBookCopiesException> { Library.checkIfBookCanBeBorrowed(book) }
    }
}