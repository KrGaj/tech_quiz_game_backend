package com.example.routing

import com.example.data.dto.TokenData
import com.example.data.repository.UserRepository
import com.example.data.resources.User
import com.example.plugins.GOOGLE_AUTH_NAME
import com.example.util.Success
import io.ktor.server.application.Application
import io.ktor.server.auth.authenticate
import io.ktor.server.auth.principal
import io.ktor.server.resources.get
import io.ktor.server.response.respond
import io.ktor.server.routing.Route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.routingUser() {
    val repository: UserRepository by inject()

    routing {
        authenticate(GOOGLE_AUTH_NAME) {
            configureUserRouting(repository)
        }
    }
}

private fun Route.configureUserRouting(
    repository: UserRepository,
) = get<User> {
    val tokenData = call.principal<TokenData>()
    val response = repository.getOrCreateUser(tokenData!!) as Success

    call.respond(response.data)
}
