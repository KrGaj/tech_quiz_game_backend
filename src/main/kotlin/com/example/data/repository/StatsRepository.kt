package com.example.data.repository

import com.example.data.dto.stats.CategoryStatsDTO
import com.example.data.dto.stats.CorrectAnswersStatsDTO
import java.util.*

interface StatsRepository {
    fun getMostAnsweredCategories(
        userUUID: UUID,
        count: Int,
    ): List<CategoryStatsDTO>

    fun getCorrectAnswersCount(
        userUUID: UUID,
    ): CorrectAnswersStatsDTO
}
