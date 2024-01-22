package com.example.data.dto.stats

import kotlinx.serialization.Serializable

@Serializable
data class AnsweredQuestionsCountStats(
    val questionsAnswered: Int,
    val allQuestions: Int,
)
