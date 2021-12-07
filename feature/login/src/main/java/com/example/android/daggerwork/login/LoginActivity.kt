package com.example.android.daggerwork.login

import android.os.Bundle
import android.widget.Toast
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android.daggerwork.dagger.viewmodel.ViewModelFactory
import com.example.android.daggerwork.domain.features.LoginFeature.LoginResult.LoggedIn
import com.example.android.daggerwork.login.LoginViewModel.Effect.LoginAttempt
import com.example.android.daggerwork.resources.compose.ComposeSampleTheme
import com.example.android.daggerwork.router.Router
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<LoginViewModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injector().inject(this)

        val loginViewModel by viewModels<LoginViewModel>() { viewModelFactory }

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                loginViewModel.effects.collect { effect ->
                    when (effect) {
                        is LoginAttempt -> when (val result = effect.result) {
                            is LoggedIn -> {
                                router.goTo(this@LoginActivity, result.route)
                            }
                            else -> Toast.makeText(this@LoginActivity, "Wrong Username", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        setContent {
            LoginScreen(loginViewModel)
        }
    }
}

@Composable
fun LoginScreen(viewModel: LoginViewModel) {

    val username by viewModel.username.collectAsState()
    val password by viewModel.password.collectAsState()

    ComposeSampleTheme {
        Surface(color = MaterialTheme.colors.background) {
            Row(
                modifier = Modifier.fillMaxHeight(),
                verticalAlignment = Alignment.CenterVertically) {
                LoginPassword(
                    modifier = Modifier.fillMaxWidth(),
                    username = username,
                    updateUsername = { viewModel.username.value = it },
                    password = password,
                    updatePassword = { viewModel.password.value = it },
                    login = { viewModel.login(username, password) }
                )
            }
        }
    }
}

@Composable
fun LoginPassword(
    modifier: Modifier = Modifier,
    username: String,
    updateUsername: (String) -> Unit,
    password: String,
    updatePassword: (String) -> Unit,
    login: () -> Unit,
) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        UsernameTextField(username, updateUsername)
        PasswordTextField(password, updatePassword)
        Spacer(Modifier.height(16.dp))
        Button(onClick = login, Modifier.testTag("LoginButton")) {
            Text(text = "Login")
        }
    }
}

@Composable
fun UsernameTextField(username: String, updateUsername: (String) -> Unit) {
    OutlinedTextField(
        value = username,
        singleLine = true,
        onValueChange = updateUsername,
        label = { Text("Login") },
        modifier = Modifier.testTag("UserId")
    )
}

@Composable
fun PasswordTextField(password: String, updatePassword: (String) -> Unit) {
    OutlinedTextField(
        value = password,
        onValueChange = updatePassword,
        label = { Text("Enter password") },
        visualTransformation = PasswordVisualTransformation(),
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
        singleLine = true,
    )
}

@Preview(showSystemUi = true)
@Composable
fun ContentPreview() {
    LoginScreen(viewModel())
}
