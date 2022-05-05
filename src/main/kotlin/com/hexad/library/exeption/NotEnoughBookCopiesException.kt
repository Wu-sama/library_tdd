package com.hexad.library.exeption

import com.hexad.library.model.Book

class NotEnoughBookCopiesException(book: Book, place: String) :
    RuntimeException(String.format(MSG, book.toString(), place)) {
    companion object {
        private const val MSG = "Not enough book %s copies in %s."
    }
}