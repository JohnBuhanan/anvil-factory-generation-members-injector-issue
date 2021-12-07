package com.example.android.daggerwork.chat

import com.example.android.dagger_support.InitializedScope
import com.squareup.anvil.annotations.ContributesTo


@ContributesTo(InitializedScope::class)
interface ChatComponent {
    fun inject(chatActivity: ChatActivity)
}
