package com.example.android.daggerwork.featurerunner

import android.app.Application
import com.example.android.dagger_support.InjectorProvider

abstract class FeatureRunnerApp<T> : Application(), InjectorProvider {
    abstract val component: T

    @Suppress("UNCHECKED_CAST")
    override fun <T> injector(): T {
        return component as T
    }
}
