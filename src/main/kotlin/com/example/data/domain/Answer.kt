package com.example.data.domain

import java.util.*

data class Answer(
    val id: Long = 0,
    val uuid: UUID = UUID(0, 0),
    val user: Long,
    val question: Long,
    val category: String,
    val isCorrect: Boolean,
)
