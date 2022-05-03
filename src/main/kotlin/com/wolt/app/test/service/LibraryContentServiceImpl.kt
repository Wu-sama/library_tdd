package com.wolt.app.test.service

import com.wolt.app.test.model.dto.LibraryContentDto
import org.springframework.stereotype.Service

@Service
class LibraryContentServiceImpl: LibraryContentService {
    override fun putBooksIntoLibrary(request: LibraryContentDto): LibraryContentDto {
        TODO("Not yet implemented")
    }

    override fun getBooks(): LibraryContentDto {
        TODO("Not yet implemented")
    }
}