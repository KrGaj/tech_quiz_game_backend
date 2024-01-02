package com.example.dto

import java.util.UUID

data class Answer(
    val user: UUID,
    val question: Long,
    val category: String,
    val isCorrect: Boolean,
)
