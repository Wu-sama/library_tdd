package com.hexad.library.model.dto

import javax.validation.constraints.NotBlank

data class BookDto(
    @NotBlank(message = "Name is mandatory")
    val name: String,
    @NotBlank(message = "Author name is mandatory")
    val author: String
)
