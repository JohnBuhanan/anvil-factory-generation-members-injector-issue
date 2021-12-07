package com.example.android.daggerwork

import com.example.android.daggerwork.domain.app.ApplicationData
import com.example.android.daggerwork.domain.app.SampleApplication
import com.example.android.daggerwork.domain.repositories.LabelsRepository
import kotlinx.coroutines.Deferred
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SampleApplicationImpl @Inject constructor(
    private val labelsRepo: Deferred<@JvmSuppressWildcards LabelsRepository>,
) : SampleApplication, ApplicationData {

    override suspend fun applicationData(): ApplicationData = this

    override suspend fun labelsRepository(): LabelsRepository = labelsRepo.await()
}
