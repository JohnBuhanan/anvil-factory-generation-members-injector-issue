package com.example.android.daggerwork.login.runner

import com.example.android.daggerwork.featurerunner.FeatureRunnerApp
import com.example.android.daggerwork.featurerunner.FeatureRunnerJunitRunner
import dagger.Dagger

class LoginJunitRunner : FeatureRunnerJunitRunner(TestLoginRunnerApp::class.java)

class TestLoginRunnerApp : FeatureRunnerApp<TestLoginRunnerComponent>() {
    override val component = Dagger.create(TestAppComponent::class.java).testLoginRunnerComponent()
}
