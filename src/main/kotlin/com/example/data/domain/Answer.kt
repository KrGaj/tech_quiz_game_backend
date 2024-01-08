package com.example.data.domain

import java.util.*

data class Answer(
    val id: Long,
    val uuid: UUID,
    val user: Long,
    val question: Long,
    val category: String,
    val isCorrect: Boolean,
)
