package com.example.data.dto

import java.util.UUID

data class AnswerDTO(
    val userUUID: UUID,
    val question: Question,
    val category: Category,
    val isCorrect: Boolean,
)
