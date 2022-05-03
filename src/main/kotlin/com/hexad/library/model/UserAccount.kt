package com.hexad.library.model

import com.hexad.library.exeption.LimitIsExceededException

object UserAccount {
    private val account: MutableMap<String, Int> = mutableMapOf()
    private val BOOK_LIMIT = 2

    fun addBook(book: String) {
        account.put(book, 1)
    }

    fun removeBook(book: String) {
        account.remove(book)
    }

    fun getAllBooks(): Map<String, Int> {
        return account
    }

    fun clear() {
        account.clear()
    }

    fun checkIfBookCanBeAdded() {
        if (canAddABook()) {
            throw LimitIsExceededException()
        }
    }

    private fun canAddABook(): Boolean {
        val number = account.values.sum()
        return number < BOOK_LIMIT
    }
}