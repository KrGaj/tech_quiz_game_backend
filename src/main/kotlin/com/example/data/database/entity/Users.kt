package com.example.data.database.entity

import org.jetbrains.exposed.v1.core.CustomFunction
import org.jetbrains.exposed.v1.core.UuidColumnType
import org.jetbrains.exposed.v1.core.dao.id.EntityID
import org.jetbrains.exposed.v1.core.dao.id.LongIdTable
import org.jetbrains.exposed.v1.dao.LongEntity
import org.jetbrains.exposed.v1.dao.LongEntityClass
import kotlin.uuid.ExperimentalUuidApi

class User(id: EntityID<Long>) : LongEntity(id) {
    companion object : LongEntityClass<User>(Users)

    @OptIn(ExperimentalUuidApi::class)
    var uuid by Users.uuid
    var username by Users.username
    var email by Users.email
}

object Users : LongIdTable() {
    private const val USERNAME_LENGTH = 32
    private const val EMAIL_LENGTH = 320

    @OptIn(ExperimentalUuidApi::class)
    val uuid = uuid("uuid").uniqueIndex()
        .defaultExpression(CustomFunction("uuid_generate_v4", UuidColumnType()))
    val username = varchar("username", USERNAME_LENGTH).uniqueIndex()
    val email = varchar("email", EMAIL_LENGTH).uniqueIndex()
}
