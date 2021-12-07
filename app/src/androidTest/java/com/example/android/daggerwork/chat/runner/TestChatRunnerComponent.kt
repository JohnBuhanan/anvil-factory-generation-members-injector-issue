package com.example.android.daggerwork.chat.runner

import com.example.android.dagger_support.InitializedScope
import com.squareup.anvil.annotations.MergeComponent
import com.squareup.anvil.annotations.MergeSubcomponent
import javax.inject.Singleton

@[Singleton MergeComponent(Singleton::class)]
interface TestAppComponent {
    fun testChatRunnerComponent(): TestChatRunnerComponent
}

@[InitializedScope MergeSubcomponent(InitializedScope::class)]
interface TestChatRunnerComponent : ChatRunnerComponent {
    fun inject(chatActivityTest: ChatActivityTest)
}
