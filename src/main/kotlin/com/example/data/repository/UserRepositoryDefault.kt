package com.example.data.repository

import com.example.data.database.dao.UserDAO
import com.example.data.domain.User
import com.example.data.dto.TokenData
import com.example.data.dto.UserDTO

class UserRepositoryDefault(
    private val userDAO: UserDAO,
) : UserRepository {
    override suspend fun getOrCreateUser(tokenData: TokenData): UserDTO? {
        var user = userDAO.getUser(tokenData.email)
        if (user == null) {
            user = userDAO.insertUser(
                User(
                    username = tokenData.name,
                    email = tokenData.email,
                )
            )
        }

        return user?.let {
            UserDTO(
                uuid = user.uuid,
                username = user.username,
                email = user.email,
            )
        }
    }
}