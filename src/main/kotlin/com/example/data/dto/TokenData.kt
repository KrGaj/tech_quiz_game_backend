package com.example.data.dto

import io.ktor.server.auth.*

data class TokenData(
    val name: String,
    val email: String,
) : Principal
