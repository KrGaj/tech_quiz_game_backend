package com.example.data.repository

import com.example.data.database.dao.UserDAO
import com.example.data.domain.User
import com.example.data.dto.TokenData
import com.example.data.dto.UserDTO
import com.example.util.Failure
import com.example.util.RepositoryResult
import com.example.util.Success

class UserRepositoryDefault(
    private val userDAO: UserDAO,
) : UserRepository {
    override suspend fun getOrCreateUser(
        tokenData: TokenData,
    ): RepositoryResult<UserDTO> {
        val user = userDAO.getUserByEmail(tokenData.email)
            ?: userDAO.insertUser(
                User(
                    username = tokenData.name,
                    email = tokenData.email,
                )
            )

        return user?.let {
            val dto = UserDTO(
                uuid = user.uuid,
                username = user.username,
                email = user.email,
            )
            Success(dto)
        } ?: Failure()
    }
}