package com.example.data.dto.stats

import kotlinx.serialization.Serializable

@Serializable
data class CorrectAnswersStatsDTO(
    val correctAnswers: Long,
    val allAnswers: Long,
)
