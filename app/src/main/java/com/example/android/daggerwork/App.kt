package com.example.android.daggerwork

import android.app.Application
import android.content.Context
import com.example.android.dagger_support.InjectorProvider
import com.example.android.daggerwork.di.initialized.InitComponent
import com.example.android.daggerwork.di.initializing.AppComponent
import com.example.android.daggerwork.domain.app.SampleApplication
import dagger.Dagger
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

open class App : Application(), SampleApplication, InjectorProvider {

    open val appComponent: AppComponent by lazy { Dagger.create(AppComponent::class.java) }

    @Inject
    internal lateinit var app: SampleApplication

    @[Inject JvmSuppressWildcards]
    lateinit var initComponent: Deferred<InitComponent>

    override fun onCreate() {
        super.onCreate()

        appComponent.inject(this)
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T> injector(): T {
        return runBlocking { initComponent.await() as T }
    }

    @Deprecated("kept only for legacy code. This should be dagger injected")
    override suspend fun applicationData() = app.applicationData()

    companion object {
        fun initComponent(context: Context) = (context.applicationContext as App).initComponent
    }
}

val Context.initComponent: InitComponent
    get() = runBlocking { App.initComponent(this@initComponent).await() }
