package com.hexad.library.model.dto

import javax.validation.constraints.NotBlank

data class BookDto(
    @field:NotBlank(message = "Name is mandatory")
    val name: String,
    @field:NotBlank(message = "Author name is mandatory")
    val author: String
)
