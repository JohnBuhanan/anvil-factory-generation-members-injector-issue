package com.example.android.daggerwork.featurerunner.di

import android.content.Context
import com.example.android.dagger_support.InitializedScope
import com.example.android.dagger_support.InjectorProvider
import com.example.android.daggerwork.featurerunner.session.SessionBuilderActivity
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Singleton

@ContributesTo(InitializedScope::class)
interface FeatureRunnerSupportComponent {
    fun inject(activity: SessionBuilderActivity)
}

internal fun Context.injector(): FeatureRunnerSupportComponent = (applicationContext as InjectorProvider).injector()


//TODO Move the dispatcher to another module and add qualifiers for Main, IO, and Default
@[Module ContributesTo(Singleton::class)]
object DispatchersModule {
    @Provides
    fun mainCoroutineDispatcher(): CoroutineDispatcher = Dispatchers.Main.immediate
}


