package com.example.di

import com.example.data.repository.AnswerRepository
import com.example.data.repository.AnswerRepositoryDefault
import com.example.data.repository.StatsRepository
import com.example.data.repository.StatsRepositoryDefault
import com.example.data.repository.UserRepository
import com.example.data.repository.UserRepositoryDefault
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val repositoryModule = module {
    singleOf(::AnswerRepositoryDefault) bind AnswerRepository::class
    singleOf(::UserRepositoryDefault) bind UserRepository::class
    singleOf(::StatsRepositoryDefault) bind StatsRepository::class
}
