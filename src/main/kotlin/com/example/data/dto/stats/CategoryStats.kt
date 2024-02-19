package com.example.data.dto.stats

import com.example.data.dto.Category
import kotlinx.serialization.Serializable

@Serializable
data class CategoryStats(
    val category: Category,
    val answersGiven: Int,
)
