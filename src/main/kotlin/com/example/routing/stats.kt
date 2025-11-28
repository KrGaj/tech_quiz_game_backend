package com.example.routing

import com.example.data.repository.StatsRepository
import com.example.data.resources.Stats
import com.example.plugins.GOOGLE_AUTH_NAME
import com.example.util.Success
import com.example.util.UserNotFound
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.auth.authenticate
import io.ktor.server.resources.get
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject
import kotlin.uuid.ExperimentalUuidApi

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
) {
    configureMostAnsweredCategoriesRouting(repository)
    configureCorrectAnswerCountRouting(repository)
}

@OptIn(ExperimentalUuidApi::class)
private fun Route.configureMostAnsweredCategoriesRouting(
    repository: StatsRepository,
) = get<Stats.MostAnsweredCategories> { stats ->
    val stats = repository.getMostAnsweredCategories(
        userUuid = stats.userUuid,
        count = stats.count,
    )

    val response = when (stats) {
        is Success -> stats.data
        is UserNotFound -> HttpStatusCode.Unauthorized
    }

    call.respond(response)
}

@OptIn(ExperimentalUuidApi::class)
private fun Route.configureCorrectAnswerCountRouting(
    repository: StatsRepository,
) = get<Stats.CorrectAnswersCount> { stats ->
    val stats = repository.getCorrectAnswersCount(
        userUuid = stats.userUuid,
    )

    val response = when (stats) {
        is Success -> stats.data
        is UserNotFound -> HttpStatusCode.Unauthorized
    }

    call.respond(response)
}
