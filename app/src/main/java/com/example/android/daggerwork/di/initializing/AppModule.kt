package com.example.android.daggerwork.di.initializing

import com.example.android.daggerwork.SampleApplicationImpl
import com.example.android.daggerwork.di.initialized.InitComponent
import com.example.android.daggerwork.di.initialized.LabelsRepositoryModule
import com.example.android.daggerwork.domain.app.ApplicationData
import com.example.android.daggerwork.domain.app.SampleApplication
import com.squareup.anvil.annotations.ContributesTo
import dagger.Binds
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import javax.inject.Singleton

@Module
@ContributesTo(Singleton::class)
abstract class AppModule {
    @Binds
    abstract fun bindsSampleApplication(sampleApplicationImpl: SampleApplicationImpl): SampleApplication

    @Binds
    abstract fun bindsApplicationData(sampleApplicationImpl: SampleApplicationImpl): ApplicationData

    companion object {
        @[Singleton Provides JvmStatic]
        fun provideInitComponent(
            appData: ApplicationData,
            scope: CoroutineScope,
            initComponentBuilder: InitComponent.Builder,
        ): Deferred<InitComponent> =
            scope.async {
                // Compute all dependencies in parallel
                val deferredLabels = async { appData.labelsRepository() }

                // Add them to the builder
                initComponentBuilder
                    .labelsRepositoryModule(LabelsRepositoryModule(deferredLabels.await()))
                    .build()
            }
    }
}

