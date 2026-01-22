package com.example.util

import com.example.data.database.entity.User
import com.example.data.database.entity.Users
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@OptIn(ExperimentalUuidApi::class)
fun <T> transactionForUser(
    userUuid: Uuid,
    statement: (User) -> RepositoryResult<T>,
): RepositoryResult<T> = transaction {
    val user = User.find { Users.uuid eq userUuid }.firstOrNull()

    if (user == null) {
        return@transaction UserNotFound()
    }

    statement(user)
}
