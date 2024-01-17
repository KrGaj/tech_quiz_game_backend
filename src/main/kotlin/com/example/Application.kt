package com.example

import com.example.data.database.initDatabase
import com.example.di.repositoryModule
import com.example.plugins.configureAuth
import com.example.plugins.configureMonitoring
import com.example.plugins.configureRouting
import com.example.plugins.configureSerialization
import io.ktor.server.application.Application
import io.ktor.server.application.install
import io.ktor.server.netty.EngineMain
import org.koin.ktor.plugin.Koin
import org.koin.logger.slf4jLogger

fun main(args: Array<String>) {
    EngineMain.main(args)
}

fun Application.koin() {
    install(Koin) {
        slf4jLogger()

        modules(
            repositoryModule,
        )
    }
}

fun Application.module() {
    initDatabase()
    configureAuth()
    configureMonitoring()
    configureSerialization()
    configureRouting()
}
