package com.example.android.daggerwork.nointernet.runner

import com.example.android.dagger_support.InitializedScope
import com.squareup.anvil.annotations.MergeComponent
import com.squareup.anvil.annotations.MergeSubcomponent
import javax.inject.Singleton

@[Singleton MergeComponent(Singleton::class)]
interface TestAppComponent {
    fun testNoInternetRunnerComponent(): TestNoInternetRunnerComponent
}

@[InitializedScope MergeSubcomponent(InitializedScope::class)]
interface TestNoInternetRunnerComponent : NoInternetRunnerComponent
