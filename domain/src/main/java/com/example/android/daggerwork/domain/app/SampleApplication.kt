package com.example.android.daggerwork.domain.app

import com.example.android.daggerwork.domain.repositories.LabelsRepository

interface SampleApplication {
    /**
     * Since the application might not be initialized when this is called, the function is suspending and will initialize
     * the application upon access
     */
    suspend fun applicationData(): ApplicationData
}

interface ApplicationData {
    suspend fun labelsRepository(): LabelsRepository
}
