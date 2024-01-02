package com.example.dto

import java.util.UUID

data class User(
    val uuid: UUID,
    val username: String,
    val email: String,
)
