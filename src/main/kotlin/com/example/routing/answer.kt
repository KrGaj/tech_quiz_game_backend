package com.example.routing

import com.example.data.dto.AnswerDTO
import com.example.data.repository.AnswerRepository
import com.example.plugins.AUTH_NAME
import com.example.util.Success
import com.example.util.UserNotFound
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.*
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.routingAnswer() {
    val repository: AnswerRepository by inject()

    routing {
        authenticate(AUTH_NAME) {
            route("/answers") {
                post {
                    val answers = call.receive<List<AnswerDTO>>()
                    val response = when (repository.addMultipleAnswers(answers)) {
                        is Success -> HttpStatusCode.Created
                        is UserNotFound -> HttpStatusCode.Unauthorized
                    }

                    call.respond(response)
                }
            }
        }
    }
}
