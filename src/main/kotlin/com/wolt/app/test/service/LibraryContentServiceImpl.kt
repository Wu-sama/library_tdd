package com.wolt.app.test.service

import com.wolt.app.test.model.dto.Library
import com.wolt.app.test.model.dto.LibraryContentDto
import org.springframework.stereotype.Service

@Service
class LibraryContentServiceImpl: LibraryContentService {
    companion object{
        val library = Library()
    }
    override fun putBooksIntoLibrary(request: LibraryContentDto): LibraryContentDto {
        library.addBooks(request.books)
        return LibraryContentDto(library.books)
    }

    override fun getBooks(): LibraryContentDto {
        return LibraryContentDto(library.books)
    }
}