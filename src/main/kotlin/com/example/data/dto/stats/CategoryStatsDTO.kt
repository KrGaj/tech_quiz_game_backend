package com.example.data.dto.stats

import com.example.data.dto.CategoryDTO
import kotlinx.serialization.Serializable

@Serializable
data class CategoryStatsDTO(
    val category: CategoryDTO,
    val answersGiven: Long,
)
