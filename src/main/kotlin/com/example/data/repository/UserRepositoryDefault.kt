package com.example.data.repository

import com.example.data.database.entity.User
import com.example.data.database.entity.Users
import com.example.data.dto.TokenData
import com.example.data.dto.UserDTO
import com.example.util.RepositoryResult
import com.example.util.Success
import org.jetbrains.exposed.sql.transactions.transaction

class UserRepositoryDefault : UserRepository {
    override suspend fun getOrCreateUser(
        tokenData: TokenData,
    ): RepositoryResult<UserDTO> {
        val user = transaction {
            User.find { Users.email eq tokenData.email }
                .singleOrNull()
                ?: User.new {
                    username = tokenData.name
                    email = tokenData.email
                }
        }

        return user.let {
            val dto = UserDTO(
                uuid = user.uuid,
                username = user.username,
                email = user.email,
            )
            Success(dto)
        }
    }
}
