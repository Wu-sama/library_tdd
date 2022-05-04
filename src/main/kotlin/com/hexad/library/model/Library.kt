package com.hexad.library.model

import com.hexad.library.exeption.BookNotFoundException
import com.hexad.library.exeption.NotEnoughBookCopiesException

object Library {
    private const val STORE_NAME="the library"

    private var books: Map<String, Int> = mutableMapOf()

    fun addBooks(books: Map<String, Int>) {
        Library.books = (Library.books.toList() + books.toList())
            .groupBy({ it.first }, { it.second })
            .map { (key, values) -> key to values.sum() }
            .toMap()
    }

    fun borrowBook(name: String) {
        checkIfBookCanBeBorrowed(name)
        books[name]!!.minus(1)
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
}