package com.example.data.repository

import com.example.data.dto.stats.CategoryStatsDTO
import com.example.data.dto.stats.CorrectAnswersStatsDTO
import com.example.util.RepositoryResult
import java.util.*

interface StatsRepository {
    fun getMostAnsweredCategories(
        userUUID: UUID,
        count: Int,
    ): RepositoryResult<List<CategoryStatsDTO>>

    fun getCorrectAnswersCount(
        userUUID: UUID,
    ): RepositoryResult<CorrectAnswersStatsDTO>
}
