package com.example.android.dagger_support

interface InjectorProvider {
    fun <T> injector(): T
}
