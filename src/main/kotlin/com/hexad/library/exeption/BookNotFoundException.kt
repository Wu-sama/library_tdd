package com.hexad.library.exeption

import com.hexad.library.model.Book

class BookNotFoundException(book: Book, place: String) : RuntimeException(String.format(MSG, book.toString(), place)) {
    companion object {
        private const val MSG = "Book %s not found %s."
    }

}