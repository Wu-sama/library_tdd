package com.wolt.app.test.model.dto

data class Library(
    var books: Map<String, Int> = mutableMapOf<String, Int>()
) {
    fun addBooks(books: Map<String, Int>) {
        this.books = (this.books.toList() + books.toList())
            .groupBy({ it.first }, { it.second })
            .map { (key, values) -> key to values.sum() }
            .toMap()
    }
}