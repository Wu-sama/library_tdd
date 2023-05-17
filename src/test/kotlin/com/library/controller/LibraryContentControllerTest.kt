package com.library.controller

import com.fasterxml.jackson.databind.ObjectMapper
import com.library.model.dto.BookDto
import com.ninjasquad.springmockk.MockkBean
import com.library.model.dto.LibraryContentDto
import com.library.model.dto.RecordDto
import com.library.service.LibraryContentService
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@WebMvcTest(com.library.controller.LibraryContentController::class)
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
    fun add_book_to_the_library_as_an_admin(){
        val book = BookDto( name = "book1", author = "author 1")
        every{libraryContentServiceImpl.addBook(any())} returns book

        val request: String = objectMapper.writeValueAsString(book)

        val result = this.mockMvc.perform(
            MockMvcRequestBuilders.put("$URL/book")
                .contentType(MediaType.APPLICATION_JSON)
                .content(request)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()

        val dto = result.response.contentAsString

        Assertions.assertEquals(request, dto)
        verify(exactly = 1) { libraryContentServiceImpl.addBook(book) }
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
