package com.example.android.daggerwork.router

import android.app.Activity
import com.example.android.daggerwork.routes.Route

interface Router {
    fun goTo(activity: Activity, route: Route)

    companion object {
        const val EXTRA_PARAM = "EXTRA_PARAM"
    }
}
