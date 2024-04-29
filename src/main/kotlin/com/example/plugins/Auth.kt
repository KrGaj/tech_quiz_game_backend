package com.example.plugins

import com.example.data.dto.TokenData
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import io.ktor.server.application.*
import io.ktor.server.auth.*

internal const val AUTH_NAME = "google-auth"

fun Application.configureAuth() {
    val webClientId = environment.config
        .property("ktor.auth.webClientId").getString()

    install(Authentication) {
        val verifier = GoogleIdTokenVerifier.Builder(
            NetHttpTransport(),
            GsonFactory(),
        )
            .setAudience(listOf(webClientId))
            .build()

        bearer(AUTH_NAME) {
            authenticate { credential ->
                val idToken = verifier.verify(credential.token)

                idToken?.payload?.let {
                    val email = it.email
                    val name = it["name"] as String

                    TokenData(name, email)
                }
            }
        }
    }
}
