package com.example.android.daggerwork.common

import javax.inject.Inject

fun interface Factory {
    fun foo()
}

class Bar @Inject constructor(val fooFactory: Factory)
