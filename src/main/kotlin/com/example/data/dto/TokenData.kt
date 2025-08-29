package com.example.data.dto

import kotlinx.serialization.Serializable

@Serializable
data class TokenData(
    val name: String,
    val email: String,
)
