package com.example.data.repository

import com.example.data.dto.TokenData
import com.example.data.dto.UserDTO
import com.example.util.RepositoryResult

fun interface UserRepository {
    suspend fun getOrCreateUser(
        tokenData: TokenData,
    ): RepositoryResult<UserDTO>
}
