package com.example.data.repository

import com.example.data.database.entity.User
import com.example.data.database.entity.Users
import com.example.data.dto.TokenData
import com.example.data.dto.UserDTO
import com.example.util.RepositoryResult
import com.example.util.Success
import org.jetbrains.exposed.v1.core.eq
import org.jetbrains.exposed.v1.jdbc.transactions.transaction
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.toKotlinUuid

class UserRepositoryDefault : UserRepository {
    @OptIn(ExperimentalUuidApi::class)
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
                uuid = it.uuid.toKotlinUuid(),
                username = it.username,
                email = it.email,
            )
            Success(dto)
        }
    }
}
