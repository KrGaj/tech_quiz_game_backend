package com.example.data.repository

import com.example.data.dto.stats.AnsweredQuestionsCountStats
import com.example.data.dto.stats.CategoryStats
import com.example.data.dto.stats.CorrectAnswersStats

interface StatsRepository {
    fun getMostAnsweredCategories(
        count: Int,
    ): List<CategoryStats>

    fun getAnsweredQuestionsCount(): AnsweredQuestionsCountStats

    fun getCorrectAnswersCount(): CorrectAnswersStats
}
