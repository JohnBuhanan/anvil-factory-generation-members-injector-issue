package com.example.android.daggerwork.login;

import android.content.Context
import com.example.android.dagger_support.InitializedScope;
import com.example.android.dagger_support.InjectorProvider
import com.squareup.anvil.annotations.ContributesTo;

@ContributesTo(InitializedScope::class)
interface LoginComponent {
    fun inject(activity: LoginActivity)
}

fun Context.injector(): LoginComponent = (this.applicationContext as InjectorProvider).injector()
