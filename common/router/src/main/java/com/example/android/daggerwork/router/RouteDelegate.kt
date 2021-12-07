package com.example.android.daggerwork.router

import android.app.Activity
import kotlin.properties.ReadOnlyProperty
import kotlin.reflect.KProperty

@Suppress("UNCHECKED_CAST")
class RouteDelegate<out T>(private val activity: Activity) : ReadOnlyProperty<Any, T> {
    override fun getValue(thisRef: Any, property: KProperty<*>): T {
        return activity.intent.getSerializableExtra(Router.EXTRA_PARAM) as T
    }
}

fun <T> Activity.route() = RouteDelegate<T>(this)