package com.example.data.repository

import com.example.data.database.entity.Answers
import com.example.data.dto.CategoryDTO
import com.example.data.dto.stats.CategoryStatsDTO
import com.example.data.dto.stats.CorrectAnswersStatsDTO
import com.example.util.RepositoryResult
import com.example.util.Success
import com.example.util.transactionForUser
import org.jetbrains.exposed.v1.core.*
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.selectAll
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

class StatsRepositoryDefault : StatsRepository {
    @OptIn(ExperimentalUuidApi::class)
    override fun getMostAnsweredCategories(
        userUuid: Uuid,
        count: Int,
    ): RepositoryResult<List<CategoryStatsDTO>> =
        transactionForUser(userUuid) { user ->
            val categoryAlias = Answers.category.alias("category")
            val answersCountAlias = Answers.id.count().alias("answersCount")

            val result = Answers.select(
                answersCountAlias,
                categoryAlias,
            )
                .where { Answers.user eq user.id }
                .groupBy(Answers.category)
                .orderBy(Answers.id.count(), SortOrder.DESC)
                .limit(3)
                .map {
                    CategoryStatsDTO(
                        category = CategoryDTO(name = it[categoryAlias]),
                        answersGiven = it[answersCountAlias],
                    )
                }

            Success(result)
        }

    @OptIn(ExperimentalUuidApi::class)
    override fun getCorrectAnswersCount(
        userUuid: Uuid,
    ): RepositoryResult<CorrectAnswersStatsDTO> =
        transactionForUser(userUuid) { user ->
            val correctAnswersCount = Answers.selectAll()
                .where {
                    (Answers.user eq user.id) and (Answers.isCorrect eq true)
                }.count()

            val allAnswersCount = Answers.selectAll()
                .where { Answers.user eq user.id }
                .count()

            Success(
                CorrectAnswersStatsDTO(
                    correctAnswers = correctAnswersCount,
                    allAnswers = allAnswersCount,
                )
            )
        }
}
