package com.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class QuestionDTO(
    val id: Long,
    val category: CategoryDTO,
)
