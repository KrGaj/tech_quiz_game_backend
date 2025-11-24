package com.example.util

import java.util.*
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid
import kotlin.uuid.toKotlinUuid

@OptIn(ExperimentalUuidApi::class)
fun String.toUuid(): Uuid =
    UUID.fromString(this).toKotlinUuid()
