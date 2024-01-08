package com.example.data.repository

import io.ktor.server.application.*

fun interface AnswerRepository {
    suspend fun addAnswer(call: ApplicationCall)
}