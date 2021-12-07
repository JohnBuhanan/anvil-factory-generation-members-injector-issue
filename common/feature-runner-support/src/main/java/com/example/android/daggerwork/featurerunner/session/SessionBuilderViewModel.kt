package com.example.android.daggerwork.featurerunner.session

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.daggerwork.domain.features.LoginFeature
import com.example.android.daggerwork.domain.features.LoginFeature.LoginResult.LoggedIn
import com.example.android.daggerwork.domain.features.LoginFeature.LoginResult.LoggedOut
import com.example.android.daggerwork.featurerunner.session.SessionBuilderViewModel.Effect.NavigateToPostLoginRoute
import com.example.android.daggerwork.featurerunner.session.SessionBuilderViewModel.Effect.ShowErrorDialog
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class SessionBuilderViewModel
@Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val loginFeature: LoginFeature,
) : ViewModel() {

    val username = MutableStateFlow("User")
    val password = MutableStateFlow("qwerty123")
    val rememberMe = MutableStateFlow(false)
    val effects = MutableSharedFlow<Effect>()

    fun login(username: String, password: String) {
        viewModelScope.launch(dispatcher) {
            when (loginFeature.login(username, password)) {
                is LoggedIn -> effects.emit(NavigateToPostLoginRoute)
                is LoggedOut -> effects.emit(ShowErrorDialog("Wrong username or password"))
            }
        }
    }

    sealed class Effect {
        object NavigateToPostLoginRoute : Effect()
        data class ShowErrorDialog(val message: String) : Effect()
    }
}
