package com.example.di

import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.koin.dsl.module
import org.koin.ktor.plugin.koin

fun Application.databaseModule() {
    val dbModule = module {
        val driver = environment.config.property("ktor.database.driver").toString()
        val url = environment.config.property("ktor.database.url").toString()

        single {
            Database.connect(url, driver)
        }
    }

    koin {
        modules(dbModule)
    }
}
