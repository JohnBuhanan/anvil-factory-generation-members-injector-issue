package com.example.android.daggerwork.domain.features

import com.dropbox.android.external.store4.get
import com.example.android.daggerwork.domain.models.Message
import com.example.android.daggerwork.domain.repositories.ConversationStore
import com.example.android.daggerwork.domain.session.UserDataStore

fun interface CurrentConversation {
    suspend fun messages(): List<Message>
}

internal class CurrentConversationImpl(private val userDataStore: UserDataStore, private val conversation: ConversationStore) : CurrentConversation {
    override suspend fun messages(): List<Message> {
        val currentUser = userDataStore.get(Unit).username ?: return emptyList()
        return conversation.get(currentUser).messages
    }
}
