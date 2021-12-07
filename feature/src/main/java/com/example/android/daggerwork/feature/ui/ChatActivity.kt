package com.example.android.daggerwork.feature.ui

import com.example.android.daggerwork.feature.dagger.Dependency2
import com.example.android.daggerwork.feature.dagger.Dependency3
import com.example.android.daggerwork.common.ui.BaseActivity
import javax.inject.Inject

class ChatActivity : BaseActivity() {
    @Inject
    lateinit var dependency2: Dependency2

    @Inject
    lateinit var dependency3: Dependency3
}
