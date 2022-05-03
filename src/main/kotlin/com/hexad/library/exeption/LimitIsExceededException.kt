package com.hexad.library.exeption

class LimitIsExceededException() : RuntimeException(MSG) {
    companion object {
        private val MSG = "Book limit is exceeded."
    }

}