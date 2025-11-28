package com.example.plugins

import com.example.routing.routingAnswer
import com.example.routing.routingStats
import com.example.routing.routingUser
import io.ktor.server.application.*
import io.ktor.server.resources.*

fun Application.configureRouting() {
    install(Resources)

    routingAnswer()
    routingStats()
    routingUser()
}
