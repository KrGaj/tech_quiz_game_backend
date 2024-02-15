package com.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class Question(
    val id: Long,
    val category: Category,
)
