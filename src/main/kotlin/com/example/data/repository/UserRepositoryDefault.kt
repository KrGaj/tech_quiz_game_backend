package com.example.data.repository

import com.example.data.database.dao.UserDAO
import com.example.data.dto.User

class UserRepositoryDefault(
    private val userDAO: UserDAO,
) : UserRepository {
    override suspend fun getOrCreateUser(): User {
        TODO("Not yet implemented")
    }
}