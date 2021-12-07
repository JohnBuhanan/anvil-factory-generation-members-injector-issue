package com.example.android.daggerwork.featurerunner

import android.app.Activity
import android.content.Intent
import androidx.test.core.app.ActivityScenario
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.daggerwork.router.Router
import com.example.android.daggerwork.routes.Route

@Suppress("UNCHECKED_CAST")
object TestLaunchRouterImpl {
    fun <T : Activity> goToTest(route: Route) {
        val context = InstrumentationRegistry.getInstrumentation().targetContext
        val classActivity = Class.forName(route.fqcn) as Class<T>
        val intent = Intent(context, classActivity).apply {
            putExtra(Router.EXTRA_PARAM, route)
        }

        ActivityScenario.launch<T>(intent)
    }
}
