package com.hexad.library.exeption

class BookNotFoundException(name: String) : RuntimeException(String.format(MSG, name)) {
    companion object {
        private val MSG = "Book %s not found in library."
    }

}