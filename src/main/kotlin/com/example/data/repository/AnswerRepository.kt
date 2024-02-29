package com.example.data.repository

import com.example.data.dto.AnswerDTO
import com.example.util.RepositoryResult

fun interface AnswerRepository {
    suspend fun addMultipleAnswers(
        answers: List<AnswerDTO>,
    ): RepositoryResult<Unit>
}
