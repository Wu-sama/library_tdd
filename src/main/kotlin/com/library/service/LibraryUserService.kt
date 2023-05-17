package com.library.service

import com.library.model.dto.BookDto
import com.library.model.dto.RecordDto

interface LibraryUserService {
    fun borrowBook(dto: BookDto)
    fun returnBook(dto: BookDto)
    fun getBooks(): List<RecordDto>
}