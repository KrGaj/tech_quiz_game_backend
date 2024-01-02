package com.example

import com.example.di.databaseModule
import com.example.plugins.*
import io.ktor.server.application.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.koin() {
    install(Koin) {
        slf4jLogger()
    }
}

fun Application.koinModules() {
    databaseModule()
}

fun Application.module() {
    configureMonitoring()
    configureSerialization()
    configureRouting()
}
