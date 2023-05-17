package com.library.controller

import com.library.exeption.NotEnoughBookCopiesException
import com.library.model.dto.BookDto
import com.library.model.dto.LibraryContentDto
import com.library.service.LibraryContentService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/library/content")
class LibraryContentController(
    val libraryServiceImpl: LibraryContentService
) {
    @PutMapping(consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun putBooksIntoLibrary(@RequestBody request: LibraryContentDto): ResponseEntity<LibraryContentDto> {
        validateNumbersOfBook(request)
        validateBook(request)
        val result = libraryServiceImpl.putBooksIntoLibrary(request)
        return ResponseEntity<LibraryContentDto>(result, HttpStatus.OK)
    }

    @PutMapping("/book")
    fun addBook(@RequestBody bookDto: BookDto): BookDto{
        return libraryServiceImpl.addBook(bookDto)
    }


    private fun validateBook(request: LibraryContentDto) {
        val invalidBooks = request.books.filter { it.bookDto.name.isEmpty() || it.bookDto.author.isEmpty() }
        if(invalidBooks.isNotEmpty()){
            throw IllegalArgumentException("Book name and author should not be null.")
        }
    }

    private fun validateNumbersOfBook(request: LibraryContentDto) {
        val negativeOrZeroAmount = request.books.filter { it.number < 1 }
        if (negativeOrZeroAmount.isNotEmpty()) {
            throw NotEnoughBookCopiesException(negativeOrZeroAmount, "for adding in the library")
        }
    }

    @GetMapping
    fun getBooksList(): ResponseEntity<LibraryContentDto> {
        val result = libraryServiceImpl.getBooks()
        return ResponseEntity<LibraryContentDto>(result, HttpStatus.OK)
    }
}