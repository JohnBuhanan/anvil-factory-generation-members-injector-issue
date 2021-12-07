package com.example.android.daggerwork.domain.features

import com.dropbox.android.external.store4.get
import com.example.android.daggerwork.domain.features.LoginFeature.LoginResult
import com.example.android.daggerwork.domain.features.LoginFeature.LoginResult.LoggedIn
import com.example.android.daggerwork.domain.features.LoginFeature.LoginResult.LoggedOut
import com.example.android.daggerwork.domain.repositories.LoginRequest
import com.example.android.daggerwork.domain.repositories.LoginStore
import com.example.android.daggerwork.domain.session.LoggedInSourceOfTruth
import com.example.android.daggerwork.domain.session.LoggedInStatus
import com.example.android.daggerwork.domain.session.UserData
import com.example.android.daggerwork.domain.session.UserDataSourceOfTruth
import com.example.android.daggerwork.routes.Route
import com.example.android.daggerwork.routes.Route.ChatRoute.Chat2

interface LoginFeature {
    // Activity is only required because Router requires it.
    suspend fun login(userId: String, password: String): LoginResult

    sealed class LoginResult {
        data class LoggedIn(val username: String, val route: Route) : LoginResult()
        object LoggedOut : LoginResult()
    }
}

internal class LoginFeatureImpl(
    private val loginStore: LoginStore,
    private val userData: UserDataSourceOfTruth,
    private val loggedInStatusSource: LoggedInSourceOfTruth,
) : LoginFeature {

    override suspend fun login(userId: String, password: String): LoginResult {
        val r = loginStore.get(LoginRequest(userId = userId, password = password))

        return if (r.loggedIn) {
            LoggedIn(userId, Chat2())
        } else {
            LoggedOut
        }.also {
            updateLoggedInStatus(it)
        }
    }

    private suspend fun updateLoggedInStatus(loginResult: LoginResult) {

        if (loginResult is LoggedIn) {
            userData.write(Unit, UserData(loginResult.username))
        }

        loggedInStatusSource.write(Unit, LoggedInStatus(loginResult is LoggedIn))
    }

}
