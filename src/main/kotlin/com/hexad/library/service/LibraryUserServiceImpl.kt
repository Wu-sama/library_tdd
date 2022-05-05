package com.hexad.library.service

import com.hexad.library.model.Book
import com.hexad.library.model.Library
import com.hexad.library.model.UserAccount
import com.hexad.library.model.dto.BookDto

class LibraryUserServiceImpl : LibraryUserService {
    override fun borrowBook(dto: BookDto) {
        val book = Book(dto)
        UserAccount.checkLimit()
        Library.checkIfBookCanBeBorrowed(book)

        Library.borrowBook(book)
        UserAccount.addBook(book)
    }

    override fun returnBook(dto: BookDto) {
        val book = Book(dto)
        UserAccount.checkIfBookIsPresent(book)
        Library.checkIfBookIsFromLibrary(book)
        UserAccount.returnBook(book)
        Library.returnBook(book)
    }
}