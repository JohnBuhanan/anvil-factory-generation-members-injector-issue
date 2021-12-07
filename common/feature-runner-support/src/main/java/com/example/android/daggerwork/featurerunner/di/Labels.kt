package com.example.android.daggerwork.featurerunner.di

import com.example.android.dagger_support.InitializedScope
import com.example.android.daggerwork.domain.models.ChatLabels
import com.example.android.daggerwork.domain.models.Labels
import com.example.android.daggerwork.domain.repositories.FakeLabelsRepository
import com.example.android.daggerwork.domain.repositories.LabelsRepository
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides

@[Module ContributesTo(InitializedScope::class)]
object FeatureRunnerLabelsModule {
    @Provides
    fun provideLabelsRepository(labelsOverrider: LabelsOverrider): LabelsRepository = labelsOverrider.labelsRepository

    @Provides
    fun provideLabels(labelsRepository: LabelsRepository): Labels = labelsRepository.get()

    @Provides
    fun providesChatLabels(labels: Labels): ChatLabels = labels.chatLabels

    @[Provides InitializedScope]
    fun provideLabelsRepositoryProvider(): LabelsOverrider = LabelsOverrider()
}


class LabelsOverrider {
    var labels: Labels = Labels()

    internal val labelsRepository: LabelsRepository
        get() = FakeLabelsRepository(labels)

}
