package com.example.routing

import com.example.data.dto.TokenData
import com.example.data.repository.UserRepository
import com.example.plugins.AUTH_NAME
import com.example.util.Success
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.koin.ktor.ext.inject

fun Application.routingUser() {
    val repository: UserRepository by inject()

    routing {
        authenticate(AUTH_NAME) {
            route("/user") {
                get {
                    val tokenData = call.principal<TokenData>()
                    val response = repository.getOrCreateUser(tokenData!!) as Success

                    call.respond(response.data)
                }
            }
        }
    }
}
