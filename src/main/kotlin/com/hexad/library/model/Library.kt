package com.hexad.library.model

object Library {
    private var books: Map<String, Int> = mutableMapOf()

    fun addBooks(books: Map<String, Int>) {
        Library.books = (Library.books.toList() + books.toList())
            .groupBy({ it.first }, { it.second })
            .map { (key, values) -> key to values.sum() }
            .toMap()
    }

    fun getBookList(): Map<String, Int> {
        return books.filter { it -> it.value > 0 }
    }

    fun clear(){
        this.books = mutableMapOf()
    }
}