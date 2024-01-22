package com.example.routing

import com.example.data.repository.StatsRepository
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

// TODO improve
fun Application.routingStats() {
    val repository: StatsRepository by inject()

    routing {
        route("/stats") {
            route("/most_answered_categories") {
                get {
                    val stats = repository.getMostAnsweredCategories(3)
                    call.respond(stats)
                }
            }

            route("/answered_questions_count") {
                get {
                    val stats = repository.getAnsweredQuestionsCount()
                    call.respond(stats)
                }
            }

            route("/correct_answers_count") {
                get {
                    val stats = repository.getCorrectAnswersCount()
                    call.respond(stats)
                }
            }
        }
    }
}
