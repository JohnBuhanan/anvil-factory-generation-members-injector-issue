package com.example.android.daggerwork.common.dagger

import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import javax.inject.Singleton

@[Module ContributesTo(Singleton::class)]
object CommonModule {
    fun providesC(): Dependency1 = Dependency1()
}