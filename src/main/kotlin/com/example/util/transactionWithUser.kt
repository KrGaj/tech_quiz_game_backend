package com.example.util

import com.example.data.database.entity.User
import com.example.data.database.entity.Users
import org.jetbrains.exposed.sql.transactions.transaction
import java.util.*

fun <T> transactionForUser(
    userUUID: UUID,
    statement: (User) -> RepositoryResult<T>,
): RepositoryResult<T> = transaction {
    val user = User.find { Users.uuid eq userUUID }.firstOrNull()

    if (user == null) {
        return@transaction UserNotFound()
    }

    statement(user)
}
