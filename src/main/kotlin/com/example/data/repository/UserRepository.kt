package com.example.data.repository

import com.example.data.dto.TokenData
import com.example.data.dto.UserDTO

fun interface UserRepository {
    suspend fun getOrCreateUser(tokenData: TokenData): UserDTO?
}