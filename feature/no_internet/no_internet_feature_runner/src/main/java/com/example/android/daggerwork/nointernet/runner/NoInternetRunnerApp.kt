package com.example.android.daggerwork.nointernet.runner

import android.content.Context
import com.example.android.dagger_support.InitializedScope
import com.example.android.dagger_support.InjectorProvider
import com.example.android.daggerwork.featurerunner.FeatureRunnerApp
import com.squareup.anvil.annotations.MergeComponent
import com.squareup.anvil.annotations.MergeSubcomponent
import dagger.Dagger
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
class NoInternetRunnerApp : FeatureRunnerApp<NoInternetRunnerComponent>() {
    override val component = Dagger.create(AppComponent::class.java).initComponent()
}

internal fun Context.injector(): NoInternetRunnerComponent = (applicationContext as InjectorProvider).injector()


@[Singleton MergeComponent(Singleton::class)]
interface AppComponent {
    fun initComponent(): NoInternetRunnerComponent
}

@[InitializedScope MergeSubcomponent(InitializedScope::class)]
interface NoInternetRunnerComponent {
    fun inject(activity: NoInternetRunnerActivity)
}

