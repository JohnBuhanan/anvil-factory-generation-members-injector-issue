package com.example.android.daggerwork.di

import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.GlobalScope
import javax.inject.Singleton

@Module
@ContributesTo(Singleton::class)
object ScopeModule {
    @Singleton
    @Provides
    fun provideScope(): CoroutineScope {
        return GlobalScope
    }
}
