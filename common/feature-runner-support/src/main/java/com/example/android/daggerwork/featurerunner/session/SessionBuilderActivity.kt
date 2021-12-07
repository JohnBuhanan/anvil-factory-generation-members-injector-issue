package com.example.android.daggerwork.featurerunner.session

import android.content.Context
import android.content.Intent
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
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
import com.example.android.daggerwork.dagger.viewmodel.ViewModelFactory
import com.example.android.daggerwork.featurerunner.di.injector
import com.example.android.daggerwork.featurerunner.session.SessionBuilderViewModel.Effect.NavigateToPostLoginRoute
import com.example.android.daggerwork.featurerunner.session.SessionBuilderViewModel.Effect.ShowErrorDialog
import com.example.android.daggerwork.resources.compose.ComposeSampleTheme
import com.example.android.daggerwork.router.Router
import com.example.android.daggerwork.routes.Route
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

fun Context.startSessionBuilderActivity(postLoginRoute: Route) {
    this.startActivity(Intent(this, SessionBuilderActivity::class.java).apply {
        putExtra("route", postLoginRoute)
    })
}

class SessionBuilderActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<SessionBuilderViewModel>

    private val viewModel by viewModels<SessionBuilderViewModel> { viewModelFactory }

    @InternalCoroutinesApi
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injector().inject(this)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.effects.collect { effect ->
                    when (effect) {
                        is NavigateToPostLoginRoute -> {
                            router.goTo(this@SessionBuilderActivity, intent.getSerializableExtra("route") as Route)
                        }
                        is ShowErrorDialog -> {
                            Toast.makeText(this@SessionBuilderActivity, effect.message, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }

        setContent {
            ComposeSampleTheme {
                SessionBuilderScreen(viewModel)
            }
        }
    }

    @Composable
    fun SessionBuilderScreen(viewModel: SessionBuilderViewModel) {

        val username by viewModel.username.collectAsState()
        val password by viewModel.password.collectAsState()
        val rememberMe by viewModel.rememberMe.collectAsState()

        Surface(color = MaterialTheme.colors.background) {
            Row(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically) {
                LoginPassword(
                    modifier = Modifier.fillMaxWidth(),
                    username = username,
                    updateUsername = { viewModel.username.value = it },
                    password = password,
                    updatePassword = { viewModel.password.value = it },
                    rememberMe = rememberMe,
                    updateRememberMe = { viewModel.rememberMe.value = it },
                    login = { viewModel.login(username, password) }
                )
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
        rememberMe: Boolean,
        updateRememberMe: (Boolean) -> Unit,
        login: () -> Unit,
    ) {
        Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
            UsernameTextField(username, updateUsername)
            PasswordTextField(password, updatePassword)
            Spacer(Modifier.height(16.dp))
            RememberMe(rememberMe, updateRememberMe)
            Spacer(Modifier.height(16.dp))
            Button(onClick = login, Modifier.testTag("LoginButton")) {
                Text(text = "Login")
            }


        }
    }

    @Composable
    fun RememberMe(checkedState: Boolean, onCheckedChange: (Boolean) -> Unit, modifier: Modifier = Modifier) {
        Row(modifier = modifier) {
            Text(text = "Remember me")
            Spacer(modifier = Modifier.width(8.dp))
            Checkbox(
                checked = checkedState,
                onCheckedChange = onCheckedChange
            )
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

    @Preview
    @Composable
    fun RememberLoginPasswordPreview() {
        ComposeSampleTheme {
            RememberMe(checkedState = true, onCheckedChange = {})
        }
    }
}
