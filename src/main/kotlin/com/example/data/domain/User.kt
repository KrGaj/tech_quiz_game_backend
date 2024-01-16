package com.example.data.domain

import java.util.UUID

data class User(
    val id: Long = 0,
    val uuid: UUID = UUID(0, 0),
    val username: String,
    val email: String,
)
