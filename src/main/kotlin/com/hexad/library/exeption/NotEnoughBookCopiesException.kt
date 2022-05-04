package com.hexad.library.exeption

class NotEnoughBookCopiesException(name: String, place: String) : RuntimeException(String.format(MSG, name, place)) {
    companion object {
        private const val MSG = "Not enough book %s copies in %s."
    }
}