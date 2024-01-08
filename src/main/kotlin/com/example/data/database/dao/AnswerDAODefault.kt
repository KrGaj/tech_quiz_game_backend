package com.example.data.database.dao

import com.example.data.database.entity.Answers
import com.example.data.domain.Answer
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert

class AnswerDAODefault : AnswerDAO {
    override suspend fun insertAnswer(answer: Answer): Answer? {
        val insertStatement = Answers.insert {
            it[user] = answer.user
            it[question] = answer.question
            it[category] = answer.category
            it[isCorrect] = answer.isCorrect
        }

        return insertStatement.resultedValues
            ?.singleOrNull()
            ?.let(::resultRowToAnswer)
    }

    private fun resultRowToAnswer(row: ResultRow) = Answer(
        id = row[Answers.id],
        uuid = row[Answers.uuid],
        user = row[Answers.user],
        question = row[Answers.question],
        category = row[Answers.category],
        isCorrect = row[Answers.isCorrect],
    )
}