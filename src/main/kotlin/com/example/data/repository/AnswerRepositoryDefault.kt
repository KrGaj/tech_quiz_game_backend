package com.example.data.repository

import com.example.data.database.entity.Answer
import com.example.data.database.entity.User
import com.example.data.database.entity.Users
import com.example.data.dto.AnswerDTO
import com.example.util.RepositoryResult
import com.example.util.Success
import com.example.util.UserNotFound

class AnswerRepositoryDefault : AnswerRepository {
    override suspend fun addAnswer(
        answer: AnswerDTO,
    ): RepositoryResult<Unit> {
        val user = User.find { Users.uuid eq answer.userUUID }
            .singleOrNull() ?: return UserNotFound()

        Answer.new {
            this.user = user
            question = answer.question.id
            category = answer.category.name
            isCorrect = answer.isCorrect
        }

        return Success(Unit)
    }
}
