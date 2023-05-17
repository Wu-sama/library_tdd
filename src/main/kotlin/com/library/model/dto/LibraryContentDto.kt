package com.library.model.dto

import javax.validation.constraints.NotNull

data class LibraryContentDto(
    @NotNull
    val books: List<RecordDto> = emptyList()
)