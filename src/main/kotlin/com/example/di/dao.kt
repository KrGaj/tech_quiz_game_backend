package com.example.di

import com.example.data.database.dao.AnswerDAO
import com.example.data.database.dao.AnswerDAODefault
import com.example.data.database.dao.UserDAO
import com.example.data.database.dao.UserDaoDefault
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val daoModule = module {
    singleOf(::AnswerDAODefault) bind AnswerDAO::class
    singleOf(::UserDaoDefault) bind UserDAO::class
}
