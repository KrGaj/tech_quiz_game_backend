package com.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class QuestionDTO(
    val text: String,
    val category: CategoryDTO,
)
