package com.example.android.daggerwork.common.ui

import com.example.android.daggerwork.common.dagger.B2
import javax.inject.Inject

abstract class BaseActivity2 : BaseActivity1() {
    @Inject
    lateinit var b2: B2
}
