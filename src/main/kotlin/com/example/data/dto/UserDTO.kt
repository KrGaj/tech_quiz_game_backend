package com.example.data.dto

import com.example.util.UUIDSerializer
import kotlinx.serialization.Serializable
import java.util.UUID

@Serializable
data class UserDTO(
    @Serializable(
        with = UUIDSerializer::class
    ) val uuid: UUID,
    val username: String,
    val email: String,
)
