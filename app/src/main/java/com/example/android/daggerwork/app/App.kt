package com.example.android.daggerwork.app

import android.app.Application
import com.example.android.daggerwork.feature.ui.FeatureActivity2
import com.squareup.anvil.annotations.MergeComponent
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
class App : Application()

@[Singleton MergeComponent(Singleton::class)]
interface AppComponent {
    fun inject(activity: FeatureActivity2)
}
