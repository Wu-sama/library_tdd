package com.hexad.library.service

import com.hexad.library.model.Library
import com.hexad.library.model.UserAccount
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

internal class LibraryUserServiceImplTest {

    private val libraryUserServiceImpl: LibraryUserService = LibraryUserServiceImpl()

    @BeforeEach
    private fun clearContext(){
        Library.clean()
        UserAccount.clean()
    }

    @Test
    fun borrowBook() {
        Library.addBooks(mapOf("book1" to 1))

        libraryUserServiceImpl.borrowBook("book1")

        assertEquals(1, Library.getBookList().size)
        assertEquals(0, Library.getBookList()["book1"])
        assertEquals(1, UserAccount.getAllBooks()["book1"])
    }

    @Test
    fun borrowMoreThanTwoBook() {
        Library.addBooks(mapOf("book1" to 1))
        UserAccount.addBook("book2")
        UserAccount.addBook("book2")
        val exeption = assertThrows(
            RuntimeException::class.java,
            { libraryUserServiceImpl.borrowBook("book1") },
            "Expected doThing() to throw, but it didn't"
        )
        assertNotNull(exeption.message)
        assertTrue(exeption.message!!.contains("no more than 2."))
        assertNull(UserAccount.getAllBooks()["book1"])
        assertEquals(1, Library.getBookList()["book1"])
    }

    @Test
    fun borrowBookNotAddedInLibrary() {
        Library.addBooks(mapOf("book1" to 1))
        val exeption = assertThrows(
            RuntimeException::class.java,
            { libraryUserServiceImpl.borrowBook("book2") },
            "Expected doThing() to throw, but it didn't"
        )
        assertNotNull(exeption.message)
        assertTrue(exeption.message!!.contains("not found in library."))
        assertNull(UserAccount.getAllBooks()["book2"])
        assertNull(Library.getBookList()["book2"])
    }

    @Test
    fun returnCopyOfBook() {
        Library.addBooks(mapOf("book1" to 1))
        UserAccount.addBook("book1")

        libraryUserServiceImpl.returnBook("book1")

        assertEquals(1, Library.getBookList().size)
        assertEquals(2, Library.getBookList()["book1"])
        assertNull(UserAccount.getAllBooks()["book1"])
    }

    @Test
    fun returnBook() {
        Library.addBooks(mapOf("book1" to 0))
        Library.addBooks(mapOf("book2" to 1))
        UserAccount.addBook("book1")
        UserAccount.addBook("book2")

        libraryUserServiceImpl.returnBook("book1")

        assertEquals(2, Library.getBookList().size)
        assertEquals(1, Library.getBookList()["book1"])
        assertNull(UserAccount.getAllBooks()["book1"])
        assertEquals(1, UserAccount.getAllBooks().size)
    }

    @Test
    fun returnBookWhatHasNotGotFromLibrary() {
        Library.addBooks(mapOf("book1" to 1))
        UserAccount.addBook("book3")

        val exeption = assertThrows(
            RuntimeException::class.java,
            { libraryUserServiceImpl.returnBook("book3") },
            "Expected doThing() to throw, but it didn't"
        )

        assertNotNull(exeption.message)
        assertTrue(exeption.message!!.contains("not found in library."))
        assertEquals(1, Library.getBookList().size)
        assertNull(Library.getBookList()["book1"])
    }

    @Test
    fun returnNotFoundBook() {
        Library.addBooks(mapOf("book1" to 1))
        UserAccount.addBook("book1")

        val exeption = assertThrows(
            RuntimeException::class.java,
            { libraryUserServiceImpl.returnBook("book3") },
            "Expected doThing() to throw, but it didn't"
        )

        assertNotNull(exeption.message)
        assertTrue(exeption.message!!.contains("not found in user account."))
        assertEquals(1, Library.getBookList().size)
        assertEquals(1, UserAccount.getAllBooks().size)
    }

}