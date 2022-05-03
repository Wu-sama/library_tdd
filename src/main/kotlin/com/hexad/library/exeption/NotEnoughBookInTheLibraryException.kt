package com.hexad.library.exeption

class NotEnoughBookInTheLibraryException(name: String) : RuntimeException(String.format(MSG, name)) {
    companion object {
        private val MSG = "Not enough book %s in library."
    }
}