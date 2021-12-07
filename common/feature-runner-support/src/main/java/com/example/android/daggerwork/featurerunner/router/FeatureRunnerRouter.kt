package com.example.android.daggerwork.featurerunner.router

import android.app.Activity
import android.content.Intent
import android.widget.Toast
import com.example.android.daggerwork.router.Router
import com.example.android.daggerwork.router.RouterModule
import com.example.android.daggerwork.routes.Route
import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module
import javax.inject.Inject
import javax.inject.Singleton

class FeatureRunnerRouter @Inject constructor() : Router {
    override fun goTo(activity: Activity, route: Route) {
        try {
            val intent = Intent().apply { setClass(activity, Class.forName(route.fqcn)) }.putExtra(Router.EXTRA_PARAM, route)
            activity.startActivity(intent)
        } catch (e: ClassNotFoundException) {
            Toast.makeText(activity, "Route path not found: $route", Toast.LENGTH_SHORT).show()
        }
    }
}


@Module
@ContributesTo(
    scope = Singleton::class,
    replaces = [RouterModule::class])
abstract class FeatureRunnerRouterModule {
    @Binds
    internal abstract fun bindRouter(featureRunnerRouter: FeatureRunnerRouter): Router
}
