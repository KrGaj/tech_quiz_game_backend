package com.example.routing

import com.example.data.dto.TokenData
import com.example.data.repository.UserRepository
import com.example.util.Failure
import com.example.util.Success
import io.ktor.http.HttpStatusCode
import io.ktor.server.application.Application
import io.ktor.server.application.call
import io.ktor.server.auth.principal
import io.ktor.server.response.respond
import io.ktor.server.routing.get
import io.ktor.server.routing.route
import io.ktor.server.routing.routing
import org.koin.ktor.ext.inject

internal fun Application.routingUser() {
    val repository by inject<UserRepository>()

    routing {
        route("/user") {
            get {
                val tokenData = call.principal<TokenData>()
                if (tokenData == null) call.respond(HttpStatusCode.Unauthorized)

                when(
                    val result = repository.getOrCreateUser(tokenData!!)
                ) {
                    is Success -> call.respond(result.data)
                    is Failure -> call.respond(HttpStatusCode.InternalServerError)
                    else -> Unit
                }
            }
        }
    }
}
