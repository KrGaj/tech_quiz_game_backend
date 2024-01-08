package com.example.plugins

import com.example.routing.routingAnswer
import io.ktor.server.application.*

fun Application.configureRouting() {
    routingAnswer()
}
