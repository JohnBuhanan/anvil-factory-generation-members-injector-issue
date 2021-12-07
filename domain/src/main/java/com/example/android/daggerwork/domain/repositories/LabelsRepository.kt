package com.example.android.daggerwork.domain.repositories

import com.example.android.daggerwork.domain.models.Labels

interface LabelsRepository {
    fun get(): Labels
}

class FakeLabelsRepository(private val labels: Labels = Labels()) : LabelsRepository {
    override fun get() = labels
}
