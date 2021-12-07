package com.example.android.daggerwork.common.ui

import androidx.appcompat.app.AppCompatActivity
import com.example.android.daggerwork.common.dagger.Dependency1
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var dependency1: Dependency1
}
