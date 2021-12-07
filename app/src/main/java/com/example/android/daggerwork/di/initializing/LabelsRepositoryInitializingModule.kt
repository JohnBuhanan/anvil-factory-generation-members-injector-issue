package com.example.android.daggerwork.di.initializing

import com.example.android.daggerwork.domain.repositories.FakeLabelsRepository
import com.example.android.daggerwork.domain.repositories.LabelsRepository
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.delay
import javax.inject.Singleton

@Module
@ContributesTo(Singleton::class)
object LabelsRepositoryInitializingModule {
    @[Provides Singleton]
    fun labelsAsync(scope: CoroutineScope): Deferred<LabelsRepository> {
        return scope.async {
            // TODO retry mechanism can be baked in here
            delay(timeMillis = 3000) // artificial delay of initialization
            FakeLabelsRepository()
        }
    }
}
