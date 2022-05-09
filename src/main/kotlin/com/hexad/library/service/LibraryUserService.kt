package com.hexad.library.service

import com.hexad.library.model.dto.BookDto
import com.hexad.library.model.dto.RecordDto

interface LibraryUserService {
    fun borrowBook(dto: BookDto)
    fun returnBook(dto: BookDto)
    fun getBooks(): List<RecordDto>
}