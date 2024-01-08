package com.example

import com.example.data.database.initDatabase
import com.example.di.daoModule
import com.example.di.repositoryModule
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

        modules(
            daoModule,
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
