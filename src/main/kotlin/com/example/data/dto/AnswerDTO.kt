package com.example.data.dto

import com.example.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class AnswerDTO(
    @Serializable(
        with = UUIDSerializer::class
    ) val userUUID: UUID,
    val question: Question,
    val isCorrect: Boolean,
)
