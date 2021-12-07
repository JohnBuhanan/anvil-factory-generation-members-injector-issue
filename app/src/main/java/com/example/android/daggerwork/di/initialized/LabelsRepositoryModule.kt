package com.example.android.daggerwork.di.initialized

import com.example.android.dagger_support.InitializedScope
import com.example.android.daggerwork.domain.models.ChatLabels
import com.example.android.daggerwork.domain.models.Labels
import com.example.android.daggerwork.domain.repositories.LabelsRepository
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides

@Module
@ContributesTo(InitializedScope::class)
class LabelsRepositoryModule(private val labelsRepository: LabelsRepository) {

    @Provides
    @InitializedScope
    fun provideLabelsRepository() = labelsRepository

    @Provides
    fun provideLabels() = labelsRepository.get()

    @Provides
    fun providesChatLabels(labels: Labels): ChatLabels = labels.chatLabels

}
