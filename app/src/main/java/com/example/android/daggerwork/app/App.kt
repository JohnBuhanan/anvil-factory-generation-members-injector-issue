package com.example.android.daggerwork.app

import android.app.Application
import com.example.android.daggerwork.common.dagger.Components
import com.example.android.daggerwork.feature.ui.ChatActivity
import com.squareup.anvil.annotations.MergeComponent
import javax.inject.Singleton

@Suppress("UNCHECKED_CAST")
class App : Application() {
    override fun onCreate() {
        super.onCreate()

//        val thing = DaggerAppComponent.create()
//        Components.values.add(thing)
    }
}

@[Singleton MergeComponent(Singleton::class)]
interface AppComponent {
    fun inject(activity: SplashActivity)
    fun inject(activity: ChatActivity)
}
