package com.example.routing

import com.example.data.repository.StatsRepository
import com.example.plugins.GOOGLE_AUTH_NAME
import com.example.util.Success
import com.example.util.toUuid
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject
import kotlin.uuid.ExperimentalUuidApi

private const val userUuidParam = "userUuid"
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

@OptIn(ExperimentalUuidApi::class)
private fun Route.configureMostAnsweredCategoriesRouting(
    repository: StatsRepository,
) = route("/most_answered_categories") {
    get {
        val userUuid = call.request
            .queryParameters[userUuidParam]
            ?.toUuid()

        val categoriesCount = call.request
            .queryParameters[categoriesCountParam]?.toInt()

        if (userUuid == null || categoriesCount == null) {
            call.respond(HttpStatusCode.BadRequest)
        }

        val stats = repository.getMostAnsweredCategories(
            userUuid = userUuid!!,
            count = categoriesCount!!,
        ) as Success

        call.respond(stats.data)
    }
}

@OptIn(ExperimentalUuidApi::class)
private fun Route.configureCorrectAnswerCountRouting(
    repository: StatsRepository,
) = route("/correct_answers_count") {
    get {
        val userUuid = call.request
            .queryParameters[userUuidParam]
            ?.toUuid()

        if (userUuid == null) {
            call.respond(HttpStatusCode.BadRequest)
        }

        val stats = repository.getCorrectAnswersCount(
            userUuid = userUuid!!,
        ) as Success

        call.respond(stats.data)
    }
}
