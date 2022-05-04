package com.hexad.library.exeption

class BookNotFoundException(name: String, place: String) : RuntimeException(String.format(MSG, name, place)) {
    companion object {
        private const val MSG = "Book %s not found %s."
    }

}