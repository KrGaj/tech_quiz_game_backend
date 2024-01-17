package com.example.data.dto

import java.util.UUID

data class AnswerDTO(
    val userUUID: UUID,
    val question: Long,
    val category: String,
    val isCorrect: Boolean,
)
