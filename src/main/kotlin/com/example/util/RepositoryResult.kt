package com.example.util

sealed interface RepositoryResult<T>

data class Success<T>(
    val data: T,
) : RepositoryResult<T>

class UserNotFound<T> : RepositoryResult<T>

class Failure<T> : RepositoryResult<T>
