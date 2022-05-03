package com.hexad.library.service

import com.hexad.library.model.Library
import com.hexad.library.model.dto.LibraryContentDto
import org.springframework.stereotype.Service

@Service
class LibraryContentServiceImpl: LibraryContentService {
    override fun putBooksIntoLibrary(request: LibraryContentDto): LibraryContentDto {
        Library.addBooks(request.books)
        return LibraryContentDto(Library.getBookList())
    }

    override fun getBooks(): LibraryContentDto {
        return LibraryContentDto(Library.getBookList())
    }
}