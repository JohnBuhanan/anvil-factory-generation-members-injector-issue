package com.example.android.daggerwork.common.dagger

import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@[Module ContributesTo(Singleton::class)]
object CommonModule {
    @Provides
    fun providesB1(): B1 = B1()

    @Provides
    fun providesB2(): B2 = B2()
}