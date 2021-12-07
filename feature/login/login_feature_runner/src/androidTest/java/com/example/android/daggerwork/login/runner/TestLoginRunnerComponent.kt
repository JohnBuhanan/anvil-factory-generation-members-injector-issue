package com.example.android.daggerwork.login.runner

import com.example.android.dagger_support.InitializedScope
import com.squareup.anvil.annotations.MergeComponent
import com.squareup.anvil.annotations.MergeSubcomponent
import javax.inject.Singleton

@[Singleton MergeComponent(Singleton::class)]
interface TestAppComponent {
    fun testLoginRunnerComponent(): TestLoginRunnerComponent
}

@[InitializedScope MergeSubcomponent(InitializedScope::class)]
interface TestLoginRunnerComponent : LoginRunnerComponent {
    fun inject(loginActivityTest: LoginActivityTest)
}
