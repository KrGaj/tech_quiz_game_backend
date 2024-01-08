package com.example.data.domain

import java.util.UUID

data class User(
    val id: Long,
    val uuid: UUID,
    val username: String,
    val email: String,
)
