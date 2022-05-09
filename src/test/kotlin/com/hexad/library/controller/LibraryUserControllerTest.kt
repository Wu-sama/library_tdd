package com.hexad.library.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.hexad.library.model.dto.BookDto
import com.ninjasquad.springmockk.MockkBean
import com.hexad.library.model.dto.RecordDto
import com.hexad.library.service.LibraryUserService
import io.mockk.every
import io.mockk.justRun
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(LibraryUserController::class)
class LibraryUserControllerTest @Autowired constructor(
    private val mockMvc: MockMvc
) {
    @MockkBean
    lateinit var libraryUserService: LibraryUserService

    private val URL = "/library/book"

    private val objectMapper = ObjectMapper()

    @Test
    fun borrowBookFromLibrarySuccess() {
        justRun { libraryUserService.borrowBook(any()) }
        val request = BookDto(name = "Name", author = "Author")
        val result = this.mockMvc.perform(
            MockMvcRequestBuilders.put("$URL/borrowing")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        val body = result.response.contentAsString
        Assertions.assertNotNull(body)
    }

    @Test
    fun borrowBookFromLibrarySuccess_EmptyRequest() {
        justRun { libraryUserService.borrowBook(any()) }
        this.mockMvc.perform(
            MockMvcRequestBuilders.put("$URL/borrowing")
                .contentType(MediaType.APPLICATION_JSON)
                .content("")
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn()
    }

    @Test
    fun borrowBookFromLibrarySuccess_InvalidBookDto() {
        justRun { libraryUserService.borrowBook(any()) }
        val request = BookDto(name = "", author = "Author")
        this.mockMvc.perform(
            MockMvcRequestBuilders.put("$URL/borrowing")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn()
    }

    @Test
    fun returnBook() {
        justRun { libraryUserService.returnBook(any()) }
        val request = BookDto(name = "Name", author = "Author")
        val result = this.mockMvc.perform(
            MockMvcRequestBuilders.put("$URL/returning")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        val body = result.response.contentAsString
        Assertions.assertNotNull(body)
    }

    @Test
    fun returnBook_InvalidRequest() {
        justRun { libraryUserService.returnBook(any()) }
        val request = BookDto(name = "", author = "Author")
        this.mockMvc.perform(
            MockMvcRequestBuilders.put("$URL/returning")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andReturn()
    }

    @Test
    fun getUserBooks() {
        every { libraryUserService.getBooks() } returns listOf(
            RecordDto(
                bookDto = BookDto(
                    name = "Name",
                    author = "Author"
                ), number = 1
            )
        )
        val result = this.mockMvc.perform(
            MockMvcRequestBuilders.get(URL)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        val body = result.response.contentAsString
        Assertions.assertNotNull(body)
    }
}
