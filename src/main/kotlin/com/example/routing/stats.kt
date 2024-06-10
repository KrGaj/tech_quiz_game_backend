package com.example.routing

import com.example.data.repository.StatsRepository
import com.example.plugins.GOOGLE_AUTH_NAME
import com.example.util.Success
import com.example.util.toUUID
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

private const val userUUIDParam = "userUUID"
private const val categoriesCountParam = "count"

fun Application.routingStats() {
    val repository: StatsRepository by inject()

    routing {
        authenticate(GOOGLE_AUTH_NAME) {
            configureStatsRouting(repository)
        }
    }
}

private fun Route.configureStatsRouting(
    repository: StatsRepository,
) = route("/stats") {
    configureMostAnsweredCategoriesRouting(repository)
    configureCorrectAnswerCountRouting(repository)
}

private fun Route.configureMostAnsweredCategoriesRouting(
    repository: StatsRepository,
) = route("/most_answered_categories") {
    get {
        val userUUID = call.request
            .queryParameters[userUUIDParam]?.toUUID()
        val categoriesCount = call.request
            .queryParameters[categoriesCountParam]?.toInt()

        if (userUUID == null || categoriesCount == null) {
            call.respond(HttpStatusCode.BadRequest)
        }

        val stats = repository.getMostAnsweredCategories(
            userUUID = userUUID!!,
            count = categoriesCount!!,
        ) as Success

        call.respond(stats.data)
    }
}

private fun Route.configureCorrectAnswerCountRouting(
    repository: StatsRepository,
) = route("/correct_answers_count") {
    get {
        val userUUID = call.request
            .queryParameters[userUUIDParam]?.toUUID()

        if (userUUID == null) {
            call.respond(HttpStatusCode.BadRequest)
        }

        val stats = repository.getCorrectAnswersCount(
            userUUID = userUUID!!,
        ) as Success

        call.respond(stats.data)
    }
}
