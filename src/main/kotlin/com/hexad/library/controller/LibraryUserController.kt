package com.hexad.library.controller

import com.hexad.library.model.dto.BookDto
import com.hexad.library.model.dto.RecordDto
import com.hexad.library.service.LibraryUserService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/library/book")
class LibraryUserController(
    val libraryUserService: LibraryUserService
) {

    @GetMapping
    fun getBorrowedBook(): List<RecordDto> {
        return libraryUserService.getBooks()
    }

    @PutMapping("/borrowing")
    fun borrowBook(@RequestBody bookDto: BookDto){
        validateBook(bookDto)
        return libraryUserService.borrowBook(bookDto)
    }

    @PutMapping("/returning")
    fun returnBook(@RequestBody bookDto: BookDto){
        validateBook(bookDto)
        return libraryUserService.returnBook(bookDto)
    }

    fun validateBook(bookDto: BookDto){
        if (bookDto.name.isEmpty()){
            throw IllegalArgumentException("Book name should not be empty")
        }
        if (bookDto.author.isEmpty()){
            throw IllegalArgumentException("Book author should not be empty")
        }
    }
}