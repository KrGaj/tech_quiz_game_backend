package com.example.data.dto

import java.util.UUID

data class UserDTO(
    val uuid: UUID,
    val username: String,
    val email: String,
)
