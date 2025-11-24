package com.example.data.repository

import com.example.data.database.entity.Answer
import com.example.data.dto.AnswerDTO
import com.example.util.RepositoryResult
import com.example.util.Success
import com.example.util.transactionForUser
import kotlin.uuid.ExperimentalUuidApi

class AnswerRepositoryDefault : AnswerRepository {
    @OptIn(ExperimentalUuidApi::class)
    override suspend fun addMultipleAnswers(
        answers: List<AnswerDTO>,
    ): RepositoryResult<Unit> =
        transactionForUser(answers.first().userUuid) { user ->
            for (answer in answers) {
                Answer.new {
                    this.user = user
                    question = answer.question.id
                    category = answer.question.category.name
                    isCorrect = answer.isCorrect
                }
            }

            Success(Unit)
        }
}
