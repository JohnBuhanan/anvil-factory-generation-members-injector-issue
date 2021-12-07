package com.example.android.daggerwork.router

import android.app.Activity
import android.content.Intent
import com.example.android.daggerwork.routes.Route
import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module
import javax.inject.Inject
import javax.inject.Singleton

class RouterImpl @Inject constructor() : Router {
    override fun goTo(activity: Activity, route: Route) {
        try {
            val intent = Intent().apply { setClass(activity, Class.forName(route.fqcn)) }.putExtra(Router.EXTRA_PARAM, route)
            activity.startActivity(intent)
        } catch (ex: ClassNotFoundException) {
            throw RouteNotFound(route, ex)
        }
    }
}

@Module
@ContributesTo(Singleton::class)
abstract class RouterModule {
    @Binds
    abstract fun bindRouter(routerImpl: RouterImpl): Router

}
