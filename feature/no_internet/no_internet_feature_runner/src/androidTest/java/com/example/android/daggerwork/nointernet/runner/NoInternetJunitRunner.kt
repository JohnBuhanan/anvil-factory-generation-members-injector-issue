package com.example.android.daggerwork.nointernet.runner

import com.example.android.daggerwork.featurerunner.FeatureRunnerApp
import com.example.android.daggerwork.featurerunner.FeatureRunnerJunitRunner
import dagger.Dagger

class NoInternetJunitRunner : FeatureRunnerJunitRunner(app = TestNoInternetRunnerApp::class.java)

class TestNoInternetRunnerApp : FeatureRunnerApp<TestNoInternetRunnerComponent>() {
    override val component = Dagger.create(TestAppComponent::class.java).testNoInternetRunnerComponent()
}
