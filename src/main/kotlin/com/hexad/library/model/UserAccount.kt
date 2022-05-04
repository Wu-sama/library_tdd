package com.hexad.library.model

import com.hexad.library.exeption.BookNotFoundException
import com.hexad.library.exeption.LimitIsExceededException
import com.hexad.library.exeption.NotEnoughBookCopiesException

object UserAccount {
    private val account: MutableMap<String, Int> = mutableMapOf()
    private const val BOOK_LIMIT = 2
    private const val STORE_NAME="user account"

    fun addBook(book: String) {
        checkLimit()
        if (account.containsKey(book)){
            account[book] = account[book]!! + 1
        } else {
            account[book] = 1
        }
    }

    fun checkLimit() {
        if (!canAddABook()){
            throw LimitIsExceededException()
        }
    }

    fun returnBook(book: String) {
        checkIfBookIsPresent(book)
        returnBookToLibrary(book)
    }

    fun checkIfBookIsPresent(book: String) {
        if (!account.containsKey(book)) {
            throw BookNotFoundException(book, STORE_NAME)
        } else if (account[book] == null || account[book]!! < 1) {
            throw NotEnoughBookCopiesException(book, STORE_NAME)
        }
    }

    private fun returnBookToLibrary(book: String) {
        val number: Int? = account[book]
        if (number == null || number < 1) {
            throw NotEnoughBookCopiesException(book, STORE_NAME)
        } else if (number == 1) {
            account.remove(book)
        } else {
            account[book] = number - 1
        }
    }

    fun getAllBooks(): Map<String, Int> = account

    fun clean() {
        account.clear()
    }

    private fun canAddABook(): Boolean {
        val number = account.values.sum()
        return number < BOOK_LIMIT
    }
}