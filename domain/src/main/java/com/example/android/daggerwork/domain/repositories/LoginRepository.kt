package com.example.android.daggerwork.domain.repositories

import com.dropbox.android.external.store4.Store
import kotlinx.coroutines.delay

/**
 * Simulates a Retrofit service that provides a suspending method yielding a Response
 */
suspend fun login(userId: String, password: String): LoginResponse {
    delay(500)
    return loginRecords.find { it.userId == userId && it.password == password }
        ?.let { LoginResponse(loggedIn = true) }
        ?: LoginResponse(loggedIn = false)
}

typealias LoginStore = Store<LoginRequest, LoginResponse>

data class LoginRequest(val userId: String, val password: String)

data class LoginResponse(val loggedIn: Boolean)
