package com.example.android.daggerwork.di.initializing

import com.example.android.daggerwork.App
import com.example.android.daggerwork.SplashActivity
import com.example.android.daggerwork.di.initialized.InitComponent
import com.squareup.anvil.annotations.ContributesTo
import com.squareup.anvil.annotations.MergeComponent
import dagger.Module
import javax.inject.Singleton

@Singleton
@MergeComponent(Singleton::class)
interface AppComponent {

    fun initializedComponentBuilder(): InitComponent.Builder

    fun inject(app: App)
    fun inject(splashActivity: SplashActivity)
}

// Required by dagger-reflect
@Module(subcomponents = [InitComponent::class])
@ContributesTo(Singleton::class)
abstract class InitSubcomponentDefinitionModule
