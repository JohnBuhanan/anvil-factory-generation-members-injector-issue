package com.example.android.daggerwork.routes


import java.io.Serializable

/**
 * Define all routes for the app.
 */
abstract class Route(val fqcn: String) : Serializable {
    object Splash : Route("com.example.android.daggerwork.SplashActivity")

    object Login : Route("com.example.android.daggerwork.login.LoginActivity")

    sealed class ChatRoute : Route("com.example.android.daggerwork.chat.ChatActivity") {
        data class Chat2(val messages: List<String> = emptyList()) : ChatRoute()
    }

    object NoInternet : Route("com.example.android.daggerwork.nointernet.NoInternetActivity")
}
