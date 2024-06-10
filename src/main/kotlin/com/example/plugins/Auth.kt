package com.example.plugins

import com.example.data.dto.TokenData
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier
import com.google.api.client.http.javanet.NetHttpTransport
import com.google.api.client.json.gson.GsonFactory
import io.ktor.server.application.*
import io.ktor.server.auth.*

internal const val GOOGLE_AUTH_NAME = "google-auth"

fun Application.configureAuth() {
    val webClientId = environment.config
        .property("ktor.auth.webClientId").getString()
    val audience = listOf(webClientId)

    install(Authentication) {
        configureBearer(audience)
    }
}

private fun AuthenticationConfig.configureBearer(
    audience: List<String>,
) = bearer(GOOGLE_AUTH_NAME) {
    val verifier = GoogleIdTokenVerifier.Builder(
        NetHttpTransport(),
        GsonFactory(),
    )
        .setAudience(audience)
        .build()

    authenticate { credential ->
        val idToken = verifier.verify(credential.token)

        idToken?.payload?.let {
            val email = it.email
            val name = it["name"] as String

            TokenData(name, email)
        }
    }
}
