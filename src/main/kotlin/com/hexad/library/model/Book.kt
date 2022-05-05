package com.hexad.library.model

import com.hexad.library.model.dto.BookDto

data class Book(
    val name: String,
    val author: String
) {
    constructor(bookdto: BookDto) : this(
        name = bookdto.name,
        author = bookdto.author
    )

    fun toDto(): BookDto = BookDto(this.name, this.author)

}