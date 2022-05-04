package com.hexad.library.model

import com.hexad.library.exeption.BookNotFoundException
import com.hexad.library.exeption.NotEnoughBookCopiesException

object Library {
    private const val STORE_NAME = "the library"

    private var books: MutableMap<String, Int> = mutableMapOf()

    fun addBooks(books: Map<String, Int>) {
        Library.books = (Library.books.toList() + books.toList())
            .groupBy({ it.first }, { it.second })
            .map { (key, values) -> key to values.sum() }
            .toMap(mutableMapOf())
    }

    fun borrowBook(book: String) {
        checkIfBookCanBeBorrowed(book)
        books[book] = books[book]!! - 1
    }

    fun getBookList(): Map<String, Int> {
        return books.filter { it -> it.value > 0 }
    }

    fun clean() {
        this.books = mutableMapOf()
    }

    fun checkIfBookCanBeBorrowed(name: String) {
        if (!canBookBeBorrowed(name)) {
            throw NotEnoughBookCopiesException(name, STORE_NAME)
        }
    }

    private fun canBookBeBorrowed(name: String): Boolean {
        if (!books.containsKey(name)) {
            throw BookNotFoundException(name, STORE_NAME)
        }
        return books[name]!! > 0
    }

    fun checkIfBookIsFromLibrary(book: String) {
        if (!books.containsKey(book)) {
            throw BookNotFoundException(book, STORE_NAME)
        }
    }

    fun returnBook(book: String) {
        checkIfBookIsFromLibrary(book)
        val number = books[book] ?: 0
        books[book] = number + 1
    }
}