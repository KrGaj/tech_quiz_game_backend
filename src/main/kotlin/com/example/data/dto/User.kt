package com.example.data.dto

import java.util.UUID

data class User(
    val uuid: UUID,
    val username: String,
    val email: String,
)
