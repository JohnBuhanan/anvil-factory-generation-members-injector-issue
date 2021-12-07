package com.example.android.daggerwork.testrunner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

class AppAndroidJunitRunner : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, TestApp::class.qualifiedName, context)
    }
}
