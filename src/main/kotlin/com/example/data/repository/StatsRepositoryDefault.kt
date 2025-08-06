package com.example.data.repository

import com.example.data.database.entity.Answers
import com.example.data.dto.CategoryDTO
import com.example.data.dto.stats.CategoryStatsDTO
import com.example.data.dto.stats.CorrectAnswersStatsDTO
import com.example.util.RepositoryResult
import com.example.util.Success
import com.example.util.transactionForUser
import org.jetbrains.exposed.v1.core.SortOrder
import org.jetbrains.exposed.v1.core.alias
import org.jetbrains.exposed.v1.core.and
import org.jetbrains.exposed.v1.core.count
import org.jetbrains.exposed.v1.jdbc.select
import org.jetbrains.exposed.v1.jdbc.selectAll
import java.util.*

class StatsRepositoryDefault : StatsRepository {
    override fun getMostAnsweredCategories(
        userUUID: UUID,
        count: Int,
    ): RepositoryResult<List<CategoryStatsDTO>> =
        transactionForUser(userUUID) { user ->
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

    override fun getCorrectAnswersCount(
        userUUID: UUID,
    ): RepositoryResult<CorrectAnswersStatsDTO> =
        transactionForUser(userUUID) { user ->
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
