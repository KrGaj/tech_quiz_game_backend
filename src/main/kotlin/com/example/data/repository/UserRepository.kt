package com.example.data.repository

import com.example.data.dto.User

fun interface UserRepository {
    suspend fun getOrCreateUser(): User
}