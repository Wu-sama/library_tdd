package com.hexad.library.controller

import com.hexad.library.model.dto.LibraryContentDto
import com.hexad.library.service.LibraryContentService
import org.springframework.http.HttpStatus
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
    @PutMapping(consumes= ["application/json"])
    fun putBooksIntoLibrary(@RequestBody @Valid request: LibraryContentDto): ResponseEntity<LibraryContentDto> {
        val result = libraryServiceImpl.putBooksIntoLibrary(request)
        return ResponseEntity<LibraryContentDto>(result, HttpStatus.OK)
    }

    @GetMapping(consumes= ["application/json"])
    fun getBooksList(): ResponseEntity<LibraryContentDto> {
        val result = libraryServiceImpl.getBooks()
        return ResponseEntity<LibraryContentDto>(result, HttpStatus.OK)
    }
}