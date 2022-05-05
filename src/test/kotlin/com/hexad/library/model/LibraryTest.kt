package com.hexad.library.model

import com.hexad.library.exeption.BookNotFoundException
import com.hexad.library.exeption.NotEnoughBookCopiesException
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows

internal class LibraryTest {

    @BeforeEach
    fun cleanData() {
        Library.clean()
    }

    @Test
    fun addBook() {
        val book = Book(name = "book1", author = "author")
        Library.addBooks(mutableMapOf(book to 1))

        assertEquals(1, Library.getBookList().size)
        assertEquals(1, Library.getBookList()[book])
    }

    @Test
    fun addAnotherExampleOfBook() {
        val book = Book(name = "book1", author = "author")
        val addedBooks = mapOf(book to 1)
        Library.addBooks(books = mutableMapOf(book to 2))

        Library.addBooks(addedBooks)

        assertEquals(1, Library.getBookList().size)
        assertEquals(3, Library.getBookList()[book])
    }

    @Test
    fun borrowBook() {
        val book = Book(name = "book1", author = "author")
        Library.addBooks(books = mutableMapOf(book to 2))

        Library.borrowBook(book)

        assertEquals(1, Library.getBookList().size)
        assertEquals(1, Library.getBookList()[book])
    }

    @Test
    fun borrowBookNotFound() {
        val book = Book(name = "book1", author = "author")
        Library.addBooks(books = mutableMapOf(book to 2))

        assertThrows<BookNotFoundException> { Library.borrowBook(book.copy(name = "book2")) }

        assertEquals(1, Library.getBookList().size)
        assertEquals(2, Library.getBookList()[book])
    }

    @Test
    fun borrowBookNotEnoughCopies() {
        val book = Book(name = "book1", author = "author")
        Library.addBooks(books = mutableMapOf(book to 0))

        assertThrows<NotEnoughBookCopiesException> { Library.borrowBook(book) }

        assertEquals(0, Library.getBookList().size)
    }

    @Test
    fun bookCanBeBorrowed() {
        val book = Book(name = "book1", author = "author")
        Library.addBooks(mutableMapOf(book to 1))
        Library.checkIfBookCanBeBorrowed(book)
    }

    @Test
    fun bookCanNotBeBorrowed() {
        val book = Book(name = "book1", author = "author")
        Library.addBooks(mutableMapOf(book to 1))

        assertThrows<BookNotFoundException> { Library.checkIfBookCanBeBorrowed(book.copy(name = "book2")) }
    }

    @Test
    fun notEnoughBookForBorrowing() {
        val book = Book(name = "book1", author = "author")
        Library.addBooks(mutableMapOf(book to 0))

        assertThrows<NotEnoughBookCopiesException> { Library.checkIfBookCanBeBorrowed(book) }
    }

    @Test
    fun returnCopyBook() {
        val book = Book(name = "book1", author = "author")
        Library.addBooks(mutableMapOf(book to 1))

        Library.returnBook(book)

        assertEquals(2, Library.getBookList()[book])
    }

    @Test
    fun returnBook() {
        val book = Book(name = "book1", author = "author")
        Library.addBooks(mutableMapOf(book to 0))

        Library.returnBook(book)

        assertEquals(1, Library.getBookList()[book])
    }

    @Test
    fun returnBookNotFromTheLibrary() {
        val book = Book(name = "book1", author = "author")
        Library.addBooks(mutableMapOf(book to 0))

        assertThrows<BookNotFoundException> { Library.returnBook(book.copy(name = "book2")) }
    }
}