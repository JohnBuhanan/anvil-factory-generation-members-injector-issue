package com.example.android.daggerwork.featurerunner.session

import app.cash.turbine.test
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.android.daggerwork.domain.features.LoginFeature
import com.example.android.daggerwork.domain.features.LoginFeature.LoginResult
import com.example.android.daggerwork.domain.features.LoginFeature.LoginResult.LoggedIn
import com.example.android.daggerwork.domain.features.LoginFeature.LoginResult.LoggedOut
import com.example.android.daggerwork.featurerunner.session.SessionBuilderViewModel.Effect.NavigateToPostLoginRoute
import com.example.android.daggerwork.featurerunner.session.SessionBuilderViewModel.Effect.ShowErrorDialog
import com.example.android.daggerwork.routes.Route.ChatRoute.Chat2
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test
import kotlin.time.ExperimentalTime

@[ExperimentalCoroutinesApi ExperimentalTime]
class SessionBuilderViewModelTest {
    private val loginFeature = FakeLoginFeature()
    private val viewModel = SessionBuilderViewModel(TestCoroutineDispatcher(), loginFeature)

    @Test
    fun `Successful logins emits the NavigateToPostLogin effect`() = runBlockingTest {
        loginFeature.loginResult = LoggedIn("user", Chat2())

        viewModel.effects.test {
            viewModel.login("user", "password")
            assertThat(expectMostRecentItem()).isEqualTo(NavigateToPostLoginRoute)
        }
    }

    @Test
    fun `Failed logins emits the ShowMessage effect`() = runBlockingTest {
        loginFeature.loginResult = LoggedOut

        viewModel.effects.test {
            viewModel.login("user", "password")
            assertThat(expectMostRecentItem()).isEqualTo(ShowErrorDialog("Wrong username or password"))
        }
    }
}


private class FakeLoginFeature : LoginFeature {
    lateinit var loginResult: LoginResult

    override suspend fun login(userId: String, password: String): LoginResult {
        return loginResult
    }

}

