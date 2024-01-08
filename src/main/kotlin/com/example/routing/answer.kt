package com.example.routing

import com.example.data.repository.AnswerRepository
import io.ktor.server.application.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

internal fun Application.routingAnswer() {
    val repository by inject<AnswerRepository>()

    routing {
        route("/answer") {
            post {
                repository.addAnswer(call)
            }
        }
    }
}
