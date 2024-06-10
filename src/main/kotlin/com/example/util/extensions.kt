package com.example.util

import java.util.*

fun String.toUUID(): UUID =
    UUID.fromString(this)
