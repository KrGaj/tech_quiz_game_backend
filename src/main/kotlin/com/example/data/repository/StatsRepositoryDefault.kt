package com.example.data.repository

import com.example.data.dto.stats.AnsweredQuestionsCountStats
import com.example.data.dto.stats.CategoryStats
import com.example.data.dto.stats.CorrectAnswersStats

// TODO write proper logic
class StatsRepositoryDefault : StatsRepository {
    override fun getMostAnsweredCategories(
        count: Int,
    ): List<CategoryStats> {
        return listOf(
            CategoryStats("Test1", 21),
            CategoryStats("Test2", 3),
            CategoryStats("Test3", 7),
        )
    }

    override fun getAnsweredQuestionsCount(): AnsweredQuestionsCountStats {
        return AnsweredQuestionsCountStats(7, 15)
    }

    override fun getCorrectAnswersCount(): CorrectAnswersStats {
        return CorrectAnswersStats(9, 25)
    }
}