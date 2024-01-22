package com.example.data.dto.stats

import kotlinx.serialization.Serializable

@Serializable
data class CategoryStats(
    val category: String,
    val answersGiven: Int,
)
