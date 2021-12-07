package com.example.android.daggerwork

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.daggerwork.domain.session.LoggedInSourceOfTruth
import com.example.android.daggerwork.router.Router
import com.example.android.daggerwork.routes.Route.ChatRoute.Chat2
import com.example.android.daggerwork.routes.Route.Login
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import javax.inject.Inject

class SplashActivity : AppCompatActivity() {

    @Inject
    lateinit var scope: CoroutineScope

    @Inject
    lateinit var router: Router

    @Inject
    lateinit var loggedIn: LoggedInSourceOfTruth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splashscreen)

        (application as App).appComponent.inject(this)

        scope.launch {
            /** Param */
            val route = if (isUserLoggedIn()) {
                Chat2(listOf("Yo", "Wussup?", "Wuuuusssssuuuuuppp???"))
            } else {
                Login
            }

            router.goTo(this@SplashActivity, route)

            finish()
        }
    }

    private suspend fun isUserLoggedIn(): Boolean {
        return loggedIn.reader(Unit).first()?.status ?: false
    }
}
