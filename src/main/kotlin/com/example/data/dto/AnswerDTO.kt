package com.example.data.dto

import kotlinx.serialization.Serializable
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@Serializable
@OptIn(ExperimentalUuidApi::class)
data class AnswerDTO(
    val userUuid: Uuid,
    val question: QuestionDTO,
    val isCorrect: Boolean,
)
