package com.example.data.database.entity

import org.jetbrains.exposed.sql.Table

object Users : Table() {
    val id = long("id").autoIncrement()
    val uuid = uuid("uuid")
    val username = varchar("username", 32)
    val email = varchar("email", 320)

    override val primaryKey = PrimaryKey(id)
}
