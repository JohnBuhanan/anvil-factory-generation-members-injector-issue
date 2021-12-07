package com.example.android.daggerwork.feature.dagger

import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import javax.inject.Singleton

@[Module ContributesTo(Singleton::class)]
object FeatureModule {
    fun providesDependency2(): Dependency2 = Dependency2()
    fun providesDependency3(): Dependency3 = Dependency3()
}