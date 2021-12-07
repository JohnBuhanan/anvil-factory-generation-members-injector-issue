package com.example.android.daggerwork.nointernet.runner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.daggerwork.router.Router
import com.example.android.daggerwork.routes.Route.NoInternet
import javax.inject.Inject

class NoInternetRunnerActivity : AppCompatActivity() {

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injector().inject(this)

        router.goTo(this, NoInternet)
        finish()
    }
}
