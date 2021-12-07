package com.example.android.daggerwork.chat.runner

import com.example.android.daggerwork.featurerunner.FeatureRunnerApp
import com.example.android.daggerwork.featurerunner.FeatureRunnerJunitRunner
import dagger.Dagger

class ChatJunitRunner : FeatureRunnerJunitRunner(app = TestChatRunnerApp::class.java)

class TestChatRunnerApp : FeatureRunnerApp<TestChatRunnerComponent>() {
    override val component = Dagger.create(TestAppComponent::class.java).testChatRunnerComponent()
}
