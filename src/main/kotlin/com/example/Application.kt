package com.example

import com.example.data.database.initDatabase
import com.example.di.repositoryModule
import com.example.plugins.configureAuth
import com.example.plugins.configureMonitoring
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.server.application.*
import io.ktor.server.netty.*
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.koin() {
    install(Koin) {
        slf4jLogger()

        modules(
            repositoryModule,
        )
    }
}

fun Application.module() {
    configureAuth()
    configureMonitoring()
    configureRouting()
    configureSerialization()
    initDatabase()
}
