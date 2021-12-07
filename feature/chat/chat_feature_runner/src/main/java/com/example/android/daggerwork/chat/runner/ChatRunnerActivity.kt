package com.example.android.daggerwork.chat.runner

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.android.daggerwork.featurerunner.session.startSessionBuilderActivity
import com.example.android.daggerwork.routes.Route.ChatRoute.Chat2

class ChatRunnerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        startSessionBuilderActivity(postLoginRoute = Chat2(listOf("Yo", "Wussup?", "Wuuuusssssuuuuuppp???")))

        finish()
    }
}
