package com.example.data.dto.stats

import kotlinx.serialization.Serializable

@Serializable
data class CorrectAnswersStats(
    val correctAnswers: Int,
    val allAnswers: Int,
)
