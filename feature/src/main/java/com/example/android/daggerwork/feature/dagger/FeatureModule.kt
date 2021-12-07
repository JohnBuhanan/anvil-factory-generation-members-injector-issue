package com.example.android.daggerwork.feature.dagger

import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@[Module ContributesTo(Singleton::class)]
object FeatureModule {
    @Provides
    fun providesF1(): F1 = F1()

    @Provides
    fun providesF2(): F2 = F2()
}