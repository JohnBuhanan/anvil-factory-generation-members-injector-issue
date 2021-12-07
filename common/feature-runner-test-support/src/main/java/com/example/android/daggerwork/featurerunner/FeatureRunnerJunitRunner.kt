package com.example.android.daggerwork.featurerunner

import android.app.Application
import android.content.Context
import androidx.test.runner.AndroidJUnitRunner

open class FeatureRunnerJunitRunner(private val app: Class<out Any>) : AndroidJUnitRunner() {
    override fun newApplication(cl: ClassLoader?, className: String?, context: Context?): Application {
        return super.newApplication(cl, app.canonicalName, context)
    }
}
