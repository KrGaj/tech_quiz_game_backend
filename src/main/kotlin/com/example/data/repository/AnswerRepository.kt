package com.example.data.repository

import com.example.data.dto.AnswerDTO
import com.example.util.RepositoryResult

fun interface AnswerRepository {
    suspend fun addAnswer(
        answer: AnswerDTO,
    ): RepositoryResult<Unit>
}
