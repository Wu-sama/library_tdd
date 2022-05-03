package com.hexad.library.model

import com.hexad.library.exeption.BookNotFoundException
import com.hexad.library.exeption.NotEnoughBookInTheLibraryException

object Library {
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

    fun clear() {
        this.books = mutableMapOf()
    }

    fun checkIfBookCanBeBorrowed(name: String) {
        if (!canBookBeBorrowed(name)) {
            throw NotEnoughBookInTheLibraryException(name)
        }
    }

    private fun canBookBeBorrowed(name: String): Boolean {
        if (!books.containsKey(name)) {
            throw BookNotFoundException(name)
        }
        return books[name]!! > 0
    }
}