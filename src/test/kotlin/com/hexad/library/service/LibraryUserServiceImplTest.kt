package com.hexad.library.service

import com.hexad.library.exeption.BookNotFoundException
import com.hexad.library.exeption.LimitIsExceededException
import com.hexad.library.model.Book
import com.hexad.library.model.Library
import com.hexad.library.model.UserAccount
import com.hexad.library.model.dto.BookDto
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.assertThrows

internal class LibraryUserServiceImplTest {

    private val libraryUserServiceImpl: LibraryUserService = LibraryUserServiceImpl()

    @BeforeEach
    private fun clearContext() {
        Library.clean()
        UserAccount.clean()
    }

    @Test
    fun borrowBook() {
        val book = Book(name = "book1", author = "author")
        Library.addBooks(mapOf(book to 1))

        libraryUserServiceImpl.borrowBook(book.toDto())

        assertTrue(Library.getBookList().isEmpty())
        assertNull(Library.getBookList()[book])
        assertEquals(1, UserAccount.getAllBooks()[book])
    }

    @Test
    fun borrowMoreThanTwoBook() {
        val book = Book(name = "book1", author = "author")
        val book2 = Book(name = "book2", author = "author")

        Library.addBooks(mapOf(book to 1))
        UserAccount.addBook(book2)
        UserAccount.addBook(book2)

        assertThrows<LimitIsExceededException> { libraryUserServiceImpl.borrowBook(book.toDto()) }

        assertNull(UserAccount.getAllBooks()[book])
        assertEquals(1, Library.getBookList()[book])
    }

    @Test
    fun borrowBookNotAddedInLibrary() {
        val book = Book(name = "book1", author = "author")
        val book2 = Book(name = "book2", author = "author")

        Library.addBooks(mapOf(book to 1))

        assertThrows<BookNotFoundException> { libraryUserServiceImpl.borrowBook(book2.toDto()) }

        assertNull(UserAccount.getAllBooks()[book2])
        assertNull(Library.getBookList()[book2])
    }

    @Test
    fun returnCopyOfBook() {
        val book = Book(name = "book1", author = "author")

        Library.addBooks(mapOf(book to 1))
        UserAccount.addBook(book)

        libraryUserServiceImpl.returnBook(book.toDto())

        assertEquals(1, Library.getBookList().size)
        assertEquals(2, Library.getBookList()[book])
        assertNull(UserAccount.getAllBooks()[book])
    }

    @Test
    fun returnBook() {
        val book = Book(name = "book1", author = "author")
        val book2 = Book(name = "book2", author = "author")

        Library.addBooks(mapOf(book to 0))
        Library.addBooks(mapOf(book2 to 1))
        UserAccount.addBook(book)
        UserAccount.addBook(book2)

        libraryUserServiceImpl.returnBook(book.toDto())

        assertEquals(2, Library.getBookList().size)
        assertEquals(1, Library.getBookList()[book])
        assertNull(UserAccount.getAllBooks()[book])
        assertEquals(1, UserAccount.getAllBooks().size)
    }

    @Test
    fun returnBookWhatHasNotGotFromLibrary() {
        val book = Book(name = "book1", author = "author")
        val book3 = Book(name = "book3", author = "author3")

        Library.addBooks(mapOf(book to 1))
        UserAccount.addBook(book3)

        assertThrows<BookNotFoundException> { libraryUserServiceImpl.returnBook(book3.toDto()) }

        assertEquals(1, Library.getBookList().size)
        assertNull(Library.getBookList()[book3])
    }

    @Test
    fun returnNotFoundBook() {
        val book = Book(name = "book1", author = "author")
        val bookDto = BookDto(name = "book3", author = "author")
        Library.addBooks(mapOf(book to 1))

        assertThrows<BookNotFoundException> { libraryUserServiceImpl.returnBook(bookDto) }

        assertEquals(1, Library.getBookList().size)
        assertTrue(UserAccount.getAllBooks().isEmpty())
    }

}