package com.example.android.daggerwork.feature.ui

import com.example.android.daggerwork.common.ui.BaseActivity1
import com.example.android.daggerwork.feature.dagger.F1
import javax.inject.Inject

abstract class FeatureActivity1 : BaseActivity1() {
    @Inject
    lateinit var f1: F1
}
