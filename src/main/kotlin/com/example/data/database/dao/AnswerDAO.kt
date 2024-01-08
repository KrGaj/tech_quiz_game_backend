package com.example.data.database.dao

import com.example.data.domain.Answer

fun interface AnswerDAO {
    suspend fun insertAnswer(answer: Answer): Answer?
}