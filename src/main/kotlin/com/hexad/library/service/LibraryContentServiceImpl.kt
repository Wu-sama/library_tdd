package com.hexad.library.service

import com.hexad.library.model.Book
import com.hexad.library.model.Library
import com.hexad.library.model.dto.LibraryContentDto
import com.hexad.library.model.dto.RecordDto
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
}