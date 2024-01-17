package com.example.routing

import com.example.data.dto.AnswerDTO
import com.example.data.repository.AnswerRepository
import com.example.util.Success
import com.example.util.UserNotFound
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.post
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.routingAnswer() {
    val repository by inject<AnswerRepository>()

    routing {
        route("/answer") {
            post {
                val answer = call.receive<AnswerDTO>()
                val response = when(repository.addAnswer(answer)) {
                    is Success -> HttpStatusCode.Created
                    is UserNotFound -> HttpStatusCode.Unauthorized
                }

                call.respond(response)
            }
        }
    }
}
