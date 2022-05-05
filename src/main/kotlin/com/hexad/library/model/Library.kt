package com.hexad.library.model

import com.hexad.library.exeption.BookNotFoundException
import com.hexad.library.exeption.NotEnoughBookCopiesException

object Library {
    private const val STORE_NAME = "the library"

    private var books: MutableMap<Book, Int> = mutableMapOf()

    fun addBooks(books: Map<Book, Int>) {
        Library.books = (Library.books.toList() + books.toList())
            .groupBy({ it.first }, { it.second })
            .map { (key, values) -> key to values.sum() }
            .toMap(mutableMapOf())
    }

    fun borrowBook(book: Book) {
        checkIfBookCanBeBorrowed(book)
        books[book] = books[book]!! - 1
    }

    fun getBookList(): Map<Book, Int> {
        return books.filter { it -> it.value > 0 }
    }

    fun clean() {
        this.books = mutableMapOf()
    }

    fun checkIfBookCanBeBorrowed(book: Book) {
        if (!canBookBeBorrowed(book)) {
            throw NotEnoughBookCopiesException(book, STORE_NAME)
        }
    }

    private fun canBookBeBorrowed(book: Book): Boolean {
        if (!books.containsKey(book)) {
            throw BookNotFoundException(book, STORE_NAME)
        }
        return books[book]!! > 0
    }

    fun checkIfBookIsFromLibrary(book: Book) {
        if (!books.containsKey(book)) {
            throw BookNotFoundException(book, STORE_NAME)
        }
    }

    fun returnBook(book: Book) {
        checkIfBookIsFromLibrary(book)
        val number = books[book] ?: 0
        books[book] = number + 1
    }
}