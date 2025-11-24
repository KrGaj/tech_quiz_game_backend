package com.example.data.repository

import com.example.data.dto.stats.CategoryStatsDTO
import com.example.data.dto.stats.CorrectAnswersStatsDTO
import com.example.util.RepositoryResult
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

interface StatsRepository {
    @OptIn(ExperimentalUuidApi::class)
    fun getMostAnsweredCategories(
        userUuid: Uuid,
        count: Int,
    ): RepositoryResult<List<CategoryStatsDTO>>

    @OptIn(ExperimentalUuidApi::class)
    fun getCorrectAnswersCount(
        userUuid: Uuid,
    ): RepositoryResult<CorrectAnswersStatsDTO>
}
