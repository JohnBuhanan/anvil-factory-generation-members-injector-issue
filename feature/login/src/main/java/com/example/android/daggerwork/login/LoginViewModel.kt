package com.example.android.daggerwork.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.daggerwork.domain.features.LoginFeature
import com.example.android.daggerwork.domain.features.LoginFeature.LoginResult
import com.example.android.daggerwork.login.LoginViewModel.Effect.LoginAttempt
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor(private val loginFeature: LoginFeature) : ViewModel() {
    val username = MutableStateFlow("User")
    val password = MutableStateFlow("qwerty123")

    val effects = MutableSharedFlow<Effect>()

    fun login(username: String, password: String) {
        viewModelScope.launch {
            val result = loginFeature.login(username, password)
            effects.emit(LoginAttempt(result))
        }
    }

    sealed class Effect {
        data class LoginAttempt(val result: LoginResult) : Effect()
    }
}

