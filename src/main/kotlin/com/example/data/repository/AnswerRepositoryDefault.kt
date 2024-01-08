package com.example.data.repository

import com.example.data.database.dao.AnswerDAO
import io.ktor.server.application.*

class AnswerRepositoryDefault(
    private val answerDAO: AnswerDAO,
) : AnswerRepository {
    override suspend fun addAnswer(call: ApplicationCall) {
        // TODO
    }
}
