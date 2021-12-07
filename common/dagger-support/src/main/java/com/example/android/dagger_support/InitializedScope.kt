package com.example.android.dagger_support

import javax.inject.Scope
import kotlin.annotation.AnnotationRetention.RUNTIME

@Scope
@Retention(RUNTIME)
annotation class InitializedScope