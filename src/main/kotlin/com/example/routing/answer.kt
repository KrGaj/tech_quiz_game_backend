package com.example.routing

import com.example.data.dto.AnswerDTO
import com.example.data.repository.AnswerRepository
import com.example.plugins.GOOGLE_AUTH_NAME
import com.example.util.Success
import com.example.util.UserNotFound
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.routingAnswer() {
    val repository: AnswerRepository by inject()

    routing {
        authenticate(GOOGLE_AUTH_NAME) {
            configureAnswersRouting(repository)
        }
    }
}

private fun Route.configureAnswersRouting(
    repository: AnswerRepository,
) = route("/answers") {
    post {
        val answers = call.receive<List<AnswerDTO>>()
        val response = when (repository.addMultipleAnswers(answers)) {
            is Success -> HttpStatusCode.Created
            is UserNotFound -> HttpStatusCode.Unauthorized
        }

        call.respond(response)
    }
}
