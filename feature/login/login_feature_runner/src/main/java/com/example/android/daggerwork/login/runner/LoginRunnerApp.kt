package com.example.android.daggerwork.login.runner

import com.example.android.dagger_support.InitializedScope
import com.example.android.daggerwork.featurerunner.FeatureRunnerApp
import com.squareup.anvil.annotations.MergeComponent
import com.squareup.anvil.annotations.MergeSubcomponent
import dagger.Dagger
import javax.inject.Singleton

class LoginRunnerApp : FeatureRunnerApp<LoginRunnerComponent>() {
    override val component: LoginRunnerComponent = Dagger.create(AppComponent::class.java).loginRunnerComponent()
}

@[Singleton MergeComponent(Singleton::class)]
interface AppComponent {
    fun loginRunnerComponent(): LoginRunnerComponent
}

@[InitializedScope MergeSubcomponent(InitializedScope::class)]
interface LoginRunnerComponent
