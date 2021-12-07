package com.example.android.daggerwork.ui

import androidx.appcompat.app.AppCompatActivity
import com.example.android.daggerwork.router.Router
import javax.inject.Inject

abstract class BaseActivity : AppCompatActivity() {
    @Inject
    lateinit var router: Router
}
