package com.example.data.database.dao

import com.example.data.database.entity.Users
import com.example.data.domain.User
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select

class UserDaoDefault : UserDAO {
    override fun getUser(email: String): User? {
        val selectStatement = Users.select {
            Users.email eq email
        }

        return selectStatement
            .singleOrNull()
            ?.let(::resultRowToUser)
    }

    override fun insertUser(user: User): User? {
        val insertStatement = Users.insert {
            it[uuid] = user.uuid
            it[email] = user.email
            it[username] = user.username
        }

        return insertStatement.resultedValues
            ?.singleOrNull()
            ?.let(::resultRowToUser)
    }

    private fun resultRowToUser(row: ResultRow) = User(
        id = row[Users.id],
        uuid = row[Users.uuid],
        email = row[Users.email],
        username = row[Users.username],
    )
}
