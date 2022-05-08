package com.hexad.library.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.hexad.library.model.dto.BookDto
import com.ninjasquad.springmockk.MockkBean
import com.hexad.library.model.dto.LibraryContentDto
import com.hexad.library.model.dto.RecordDto
import com.hexad.library.service.LibraryContentService
import io.mockk.every
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(LibraryContentController::class)
class LibraryContentControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
) {
    @MockkBean
    lateinit var libraryContentServiceImpl: LibraryContentService

    private val URL = "/library/content"

    private val objectMapper = ObjectMapper()

    @Test
    fun putBookIntoLibrarySuccess() {
        every { libraryContentServiceImpl.putBooksIntoLibrary(any()) } returns LibraryContentDto()
        val request =
            LibraryContentDto(listOf(RecordDto(bookDto = BookDto(name = "Name", author = "Author"), number = 1)))
        val result = this.mockMvc.perform(
            MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        val body = result.response.contentAsString
        Assertions.assertNotNull(body)
    }

    @Test
    fun putBookIntoLibrary_EmptyRequest() {
        every { libraryContentServiceImpl.putBooksIntoLibrary(any()) } returns LibraryContentDto()
        val result = this.mockMvc.perform(
            MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("")
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn()
        val body = result.response.contentAsString
        Assertions.assertNotNull(body)
    }

    @Test
    fun putBookIntoLibrary_EmptyBookName() {
        every { libraryContentServiceImpl.putBooksIntoLibrary(any()) } returns LibraryContentDto()
        val request =
            LibraryContentDto(listOf(RecordDto(bookDto = BookDto(name = "", author = "Author"), number = 1)))
        val result = this.mockMvc.perform(
            MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn()
        val body = result.response.contentAsString
        Assertions.assertNotNull(body)
    }

    @Test
    fun putBookIntoLibrary_BookNumberIsZero() {
        every { libraryContentServiceImpl.putBooksIntoLibrary(any()) } returns LibraryContentDto()
        val request =
            LibraryContentDto(listOf(RecordDto(bookDto = BookDto(name = "Name", author = "Author"), number = -1)))

        val result = this.mockMvc.perform(
            MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn()
        val body = result.response.contentAsString
        Assertions.assertNotNull(body)
    }
}
