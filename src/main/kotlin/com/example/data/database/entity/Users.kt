package com.example.data.database.entity

import org.jetbrains.exposed.dao.LongEntity
import org.jetbrains.exposed.dao.LongEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.dao.id.LongIdTable
import org.jetbrains.exposed.sql.CustomFunction
import org.jetbrains.exposed.sql.UUIDColumnType

class User(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<User>(Users)
    var uuid by Users.uuid
    var username by Users.username
    var email by Users.email
}

object Users : LongIdTable() {
    private const val USERNAME_LENGTH = 32
    private const val EMAIL_LENGTH = 320

    val uuid = uuid("uuid").uniqueIndex()
        .defaultExpression(CustomFunction("uuid_generate_v4", UUIDColumnType()))
    val username = varchar("username", USERNAME_LENGTH).uniqueIndex()
    val email = varchar("email", EMAIL_LENGTH).uniqueIndex()
}
