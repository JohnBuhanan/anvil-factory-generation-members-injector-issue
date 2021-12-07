package com.example.android.daggerwork.feature.ui

import android.os.Bundle
import android.os.PersistableBundle
import com.example.android.daggerwork.feature.dagger.F2
import javax.inject.Inject

class FeatureActivity2 : FeatureActivity1() {
    @Inject
    lateinit var f2: F2

    override fun onCreate(savedInstanceState: Bundle?, persistentState: PersistableBundle?) {
        super.onCreate(savedInstanceState, persistentState)

    }
}