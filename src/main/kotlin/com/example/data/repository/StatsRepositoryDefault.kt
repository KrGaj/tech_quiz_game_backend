package com.example.data.repository

import com.example.data.dto.Category
import com.example.data.dto.stats.AnsweredQuestionsCountStats
import com.example.data.dto.stats.CategoryStats
import com.example.data.dto.stats.CorrectAnswersStats

// TODO write proper logic
class StatsRepositoryDefault : StatsRepository {
    override fun getMostAnsweredCategories(
        count: Int,
    ): List<CategoryStats> {
        return listOf(
            CategoryStats(Category("Test1"), 21),
            CategoryStats(Category("Test2"), 3),
            CategoryStats(Category("Test3"), 7),
        )
    }

    override fun getAnsweredQuestionsCount(): AnsweredQuestionsCountStats {
        return AnsweredQuestionsCountStats(7, 15)
    }

    override fun getCorrectAnswersCount(): CorrectAnswersStats {
        return CorrectAnswersStats(9, 25)
    }
}