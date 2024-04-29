package com.example.routing

import com.example.data.dto.TokenData
import com.example.data.repository.UserRepository
import com.example.plugins.AUTH_NAME
import com.example.util.Success
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.*
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

fun Application.routingUser() {
    val repository: UserRepository by inject()

    routing {
        authenticate(AUTH_NAME) {
            route("/user") {
                get {
                    val tokenData = call.principal<TokenData>()
                    if (tokenData == null) call.respond(HttpStatusCode.Unauthorized)

                    when (
                        val result = repository.getOrCreateUser(tokenData!!)
                    ) {
                        is Success -> call.respond(result.data)
                        else -> Unit
                    }
                }
            }
        }
    }
}
