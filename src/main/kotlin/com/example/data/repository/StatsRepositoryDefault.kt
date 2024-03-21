package com.example.data.repository

import com.example.data.database.entity.Answers
import com.example.data.database.entity.User
import com.example.data.database.entity.Users
import com.example.data.dto.CategoryDTO
import com.example.data.dto.stats.CategoryStatsDTO
import com.example.data.dto.stats.CorrectAnswersStatsDTO
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.UUID

class StatsRepositoryDefault : StatsRepository {
    override fun getMostAnsweredCategories(
        userUUID: UUID,
        count: Int,
    ): List<CategoryStatsDTO> =
        transaction {
            val user = User.find { Users.uuid eq userUUID }.first()

            val categoryAlias = Answers.category.alias("category")
            val answersCountAlias = Answers.id.count().alias("answersCount")

            Answers.select(
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
        }

    override fun getCorrectAnswersCount(
        userUUID: UUID,
    ): CorrectAnswersStatsDTO =
        transaction {
            val user = User.find { Users.uuid eq userUUID }.first()

            val correctAnswersCount = Answers.selectAll()
                .where {
                    (Answers.user eq user.id) and (Answers.isCorrect eq true)
                }.count()

            val allAnswersCount = Answers.selectAll()
                .where { Answers.user eq user.id }
                .count()

            CorrectAnswersStatsDTO(
                correctAnswers = correctAnswersCount,
                allAnswers = allAnswersCount,
            )
        }
}
