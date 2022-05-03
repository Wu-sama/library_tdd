package com.hexad.library.service

interface LibraryUserService {
    fun borrowBook(name: String)
    fun returnBook(name: String)
}