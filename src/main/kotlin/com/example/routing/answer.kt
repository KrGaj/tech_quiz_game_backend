package com.example.routing

import com.example.data.dto.AnswerDTO
import com.example.data.repository.AnswerRepository
import com.example.util.Failure
import com.example.util.Success
import com.example.util.UserNotFound
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.*
import io.ktor.server.request.receive
import io.ktor.server.response.respond
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

internal fun Application.routingAnswer() {
    val repository by inject<AnswerRepository>()

    routing {
        route("/answer") {
            post {
                val answer = call.receive<AnswerDTO>()
                val response = when(repository.addAnswer(answer)) {
                    is Success -> HttpStatusCode.Created
                    is UserNotFound -> HttpStatusCode.Unauthorized
                    is Failure -> HttpStatusCode.InternalServerError
                }

                call.respond(response)
            }
        }
    }
}
