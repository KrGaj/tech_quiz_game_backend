package com.example.data.database

import com.example.data.database.entity.Answers
import com.example.data.database.entity.Users
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction

fun Application.initDatabase() {
    val jdbcURL = environment.config.property("ktor.database.url").toString()
    val driverClassName = environment.config.property("ktor.database.driver").toString()
    
    val database = Database.connect(jdbcURL, driverClassName)
    transaction(database) {
        SchemaUtils.create(Users)
        SchemaUtils.create(Answers)
    }
}
