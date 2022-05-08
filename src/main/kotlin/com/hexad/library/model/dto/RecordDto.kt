package com.hexad.library.model.dto

import javax.validation.constraints.NotNull
import javax.validation.constraints.Positive

data class RecordDto(
    @NotNull
    val bookDto: BookDto,
    @NotNull
    @Positive
    val number: Int
)