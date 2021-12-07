package com.example.android.daggerwork.router

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.android.daggerwork.router.test.R
import com.example.android.daggerwork.routes.Route

data class RouterTestRoute(val message: String) : Route("com.example.android.daggerwork.router.RouterTestActivity")

class RouterTestActivity : AppCompatActivity() {
    val route by route<RouterTestRoute?>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_router_test)

        route?.let {
            findViewById<TextView>(R.id.text).text = it.message
        }
    }
}
