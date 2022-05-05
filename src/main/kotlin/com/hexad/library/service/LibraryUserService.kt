package com.hexad.library.service

import com.hexad.library.model.dto.BookDto

interface LibraryUserService {
    fun borrowBook(dto: BookDto)
    fun returnBook(dto: BookDto)
}