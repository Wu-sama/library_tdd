package com.library.service

import com.library.model.Book
import com.library.model.Library
import com.library.model.UserAccount
import com.library.model.dto.BookDto
import com.library.model.dto.RecordDto
import org.springframework.stereotype.Service

@Service
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

    override fun getBooks(): List<RecordDto> {
        return UserAccount.getAllBooks().map { RecordDto(it.key.toDto(), it.value) }
    }
}