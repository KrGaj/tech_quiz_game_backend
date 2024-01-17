package com.example.data.repository

import com.example.data.database.dao.AnswerDAO
import com.example.data.database.dao.UserDAO
import com.example.data.domain.Answer
import com.example.data.dto.AnswerDTO
import com.example.util.Failure
import com.example.util.RepositoryResult
import com.example.util.Success
import com.example.util.UserNotFound

class AnswerRepositoryDefault(
    private val answerDao: AnswerDAO,
    private val userDao: UserDAO,
) : AnswerRepository {
    override suspend fun addAnswer(
        answer: AnswerDTO,
    ): RepositoryResult<Unit> {
        val user = userDao.getUserByUUID(answer.userUUID)
            ?: return UserNotFound()

        val insertResult = answerDao.insertAnswer(
            Answer(
                user = user.id,
                question = answer.question,
                category = answer.category,
                isCorrect = answer.isCorrect,
            )
        )

        return insertResult?.let {
            Success(Unit)
        } ?: Failure()
    }
}
