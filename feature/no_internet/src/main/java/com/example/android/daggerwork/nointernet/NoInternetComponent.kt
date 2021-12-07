package com.example.android.daggerwork.nointernet

import android.content.Context
import com.example.android.dagger_support.InitializedScope
import com.example.android.dagger_support.InjectorProvider
import com.example.android.daggerwork.domain.models.Labels
import com.example.android.daggerwork.domain.models.NoInternetLabels
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides

@ContributesTo(InitializedScope::class)
interface NoInternetComponent {
    fun inject(activity: NoInternetActivity)
}


@ContributesTo(InitializedScope::class)
@Module
object NoInternetModule {
    @Provides
    fun provideLabels(labels: Labels): NoInternetLabels = labels.noInternetLabels
}


fun Context.injector(): NoInternetComponent = (this.applicationContext as InjectorProvider).injector()
