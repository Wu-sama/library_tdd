package com.hexad.library.controller

import com.hexad.library.exeption.NotEnoughBookCopiesException
import com.hexad.library.model.dto.LibraryContentDto
import com.hexad.library.service.LibraryContentService
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.Valid

@RestController
@RequestMapping("/library/content")
@Validated
class LibraryContentController(
    val libraryServiceImpl: LibraryContentService
) {
    @PutMapping(consumes= [MediaType.APPLICATION_JSON_VALUE])
    fun putBooksIntoLibrary(@RequestBody @Valid request: LibraryContentDto): ResponseEntity<LibraryContentDto> {
        validateNumbersOfBook(request)
        val result = libraryServiceImpl.putBooksIntoLibrary(request)
        return ResponseEntity<LibraryContentDto>(result, HttpStatus.OK)
    }

    private fun validateNumbersOfBook(request: LibraryContentDto) {
        val negativeOrZeroAmount = request.books.filter { it.value < 1 }
        if(negativeOrZeroAmount.isNotEmpty()){
            throw NotEnoughBookCopiesException(negativeOrZeroAmount.keys, "for adding in the library")
        }
    }

    @GetMapping
    fun getBooksList(): ResponseEntity<LibraryContentDto> {
        val result = libraryServiceImpl.getBooks()
        return ResponseEntity<LibraryContentDto>(result, HttpStatus.OK)
    }
}