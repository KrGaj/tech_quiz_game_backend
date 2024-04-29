package com.example.routing

import com.example.data.repository.StatsRepository
import com.example.plugins.AUTH_NAME
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
        authenticate(AUTH_NAME) {
            route("/stats") {
                route("/most_answered_categories") {
                    get {
                        val userUUID = call.request
                            .queryParameters[userUUIDParam]?.toUUID()
                        val categoriesCount = call.request
                            .queryParameters[categoriesCountParam]?.toInt()

                        if (userUUID == null || categoriesCount == null)
                            call.respond(HttpStatusCode.BadRequest)

                        val stats = repository.getMostAnsweredCategories(
                            userUUID = userUUID!!,
                            count = categoriesCount!!,
                        )
                        call.respond(stats)
                    }
                }

                route("/correct_answers_count") {
                    get {
                        val userUUID = call.request
                            .queryParameters[userUUIDParam]?.toUUID()

                        if (userUUID == null)
                            call.respond(HttpStatusCode.BadRequest)

                        val stats = repository.getCorrectAnswersCount(
                            userUUID = userUUID!!,
                        )
                        call.respond(stats)
                    }
                }
            }
        }
    }
}
