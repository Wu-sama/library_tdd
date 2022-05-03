package com.wolt.app.test.controller

import com.ninjasquad.springmockk.MockkBean
import com.wolt.app.test.model.dto.LibraryContentDto
import com.wolt.app.test.service.LibraryContentService
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

    @Test
    fun putBookIntoLibrarySuccess() {
        every { libraryContentServiceImpl.putBooksIntoLibrary(any()) } returns LibraryContentDto()
        val result = this.mockMvc.perform(
            MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"books\": {\n" +
                        "    \"book\": 1\n" +
                        "  }\n" +
                        "}")
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
        val result = this.mockMvc.perform(
            MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"books\": {\n" +
                        "    \"\": 1,\n" +
                        "  }\n" +
                        "}")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        val body = result.response.contentAsString
        Assertions.assertNotNull(body)
    }

    @Test
    fun putBookIntoLibrary_NullBookName() {
        every { libraryContentServiceImpl.putBooksIntoLibrary(any()) } returns LibraryContentDto()
        val result = this.mockMvc.perform(
            MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"books\": {\n" +
                        "    null: 1,\n" +
                        "  }\n" +
                        "}")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        val body = result.response.contentAsString
        Assertions.assertNotNull(body)
    }

    @Test
    fun putBookIntoLibrary_NullBookNumber() {
        every { libraryContentServiceImpl.putBooksIntoLibrary(any()) } returns LibraryContentDto()
        val result = this.mockMvc.perform(
            MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"books\": {\n" +
                        "    \"book\": null,\n" +
                        "  }\n" +
                        "}")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        val body = result.response.contentAsString
        Assertions.assertNotNull(body)
    }
    @Test
    fun putBookIntoLibrary_BookNumberBelowZero() {
        every { libraryContentServiceImpl.putBooksIntoLibrary(any()) } returns LibraryContentDto()
        val result = this.mockMvc.perform(
            MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"books\": {\n" +
                        "    \"book\": -1,\n" +
                        "  }\n" +
                        "}")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        val body = result.response.contentAsString
        Assertions.assertNotNull(body)
    }
    @Test
    fun putBookIntoLibrary_BookNumberIsZero() {
        every { libraryContentServiceImpl.putBooksIntoLibrary(any()) } returns LibraryContentDto()
        val result = this.mockMvc.perform(
            MockMvcRequestBuilders.put(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\n" +
                        "  \"books\": {\n" +
                        "    \"book\": 0,\n" +
                        "  }\n" +
                        "}")
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andReturn()
        val body = result.response.contentAsString
        Assertions.assertNotNull(body)
    }
}
