package com.library.service

import com.library.model.Book
import com.library.model.Library
import com.library.model.dto.BookDto
import com.library.model.dto.LibraryContentDto
import com.library.model.dto.RecordDto
import org.springframework.stereotype.Service

@Service
class LibraryContentServiceImpl : LibraryContentService {
    override fun putBooksIntoLibrary(request: LibraryContentDto): LibraryContentDto {
        Library.addBooks(request.books.associate { Book(it.bookDto) to it.number })
        return LibraryContentDto(toLibraryContentDto())
    }

    private fun toLibraryContentDto() = Library.getBookList().map { RecordDto(it.key.toDto(), it.value) }

    override fun getBooks(): LibraryContentDto {
        return LibraryContentDto(toLibraryContentDto())
    }

    override fun addBook(book: BookDto) : BookDto {
        return Library.addBook(Book(book)).toDto()
    }
}