package com.hexad.library.exeption

import com.hexad.library.model.Book
import com.hexad.library.model.dto.RecordDto

class NotEnoughBookCopiesException(bookName: String, additionalInfo: String) :
    RuntimeException(String.format(MSG, bookName, additionalInfo)) {
    companion object {
        private const val MSG = "Not enough book %s copies %s."
    }

    constructor(books: List<RecordDto>, additionalInfo: String) : this(
        bookName = books.toString(),
        additionalInfo = additionalInfo
    )

    constructor(book: Book, place: String) : this(bookName = book.toString(), additionalInfo = "in $place")
}