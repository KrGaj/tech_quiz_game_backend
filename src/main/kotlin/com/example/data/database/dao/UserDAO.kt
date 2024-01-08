package com.example.data.database.dao

import com.example.data.domain.User

interface UserDAO {
    fun getUser(email: String): User?
    fun insertUser(user: User): User?
}
