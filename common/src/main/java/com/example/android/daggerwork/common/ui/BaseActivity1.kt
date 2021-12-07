package com.example.android.daggerwork.common.ui

import androidx.appcompat.app.AppCompatActivity
import com.example.android.daggerwork.common.dagger.B1
import javax.inject.Inject

abstract class BaseActivity1 : AppCompatActivity() {
    @Inject
    lateinit var b1: B1
}
