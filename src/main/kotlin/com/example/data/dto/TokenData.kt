package com.example.data.dto

import io.ktor.server.auth.*
import kotlinx.serialization.Serializable

@Serializable
data class TokenData(
    val name: String,
    val email: String,
) : Principal
