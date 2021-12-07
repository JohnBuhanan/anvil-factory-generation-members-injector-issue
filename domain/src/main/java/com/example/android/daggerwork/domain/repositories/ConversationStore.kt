package com.example.android.daggerwork.domain.repositories

import com.dropbox.android.external.store4.Store
import com.example.android.daggerwork.domain.models.Message

typealias ConversationStore = Store<String, Conversation>

data class Conversation(val messages: List<Message>)


