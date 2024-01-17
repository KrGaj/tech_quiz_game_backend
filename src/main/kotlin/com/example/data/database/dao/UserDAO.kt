package com.example.data.database.dao

import com.example.data.domain.User
import java.util.UUID

interface UserDAO {
    fun getUserByEmail(email: String): User?
    fun getUserByUUID(uuid: UUID): User?
    fun insertUser(user: User): User?
}
