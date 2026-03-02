package com.example

import com.example.di.AppModule
import org.koin.core.annotation.KoinApplication

@KoinApplication(modules = [AppModule::class])
object QuizGameBackendApplication
