package com.hexad.library.model

object UserAccount {
    private val account: MutableMap<String, Int> = mutableMapOf()

    fun addBook(book: String) {
        account.put(book,1)
    }

    fun removeBook(book: String) {
        account.remove(book)
    }

    fun getAllBooks(): Map<String, Int> {
        return account
    }

    fun clear(){
        account.clear()
    }
}