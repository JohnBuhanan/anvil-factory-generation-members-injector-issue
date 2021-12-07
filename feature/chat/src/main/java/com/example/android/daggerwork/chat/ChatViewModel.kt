package com.example.android.daggerwork.chat

import com.example.android.daggerwork.domain.features.CurrentConversation
import com.example.android.daggerwork.domain.models.Message
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

interface ChatViewModel {
    val screenData: Flow<ScreenState>
    fun onAttach(scope: CoroutineScope)
    fun onDetach()
}

class ChatViewModelImpl(private val ioDispatcher: CoroutineDispatcher, private val currentConversation: CurrentConversation) : ChatViewModel {

    private val stateChannel = Channel<ScreenState>()

    override val screenData: Flow<ScreenState>
        get() = stateChannel.consumeAsFlow()

    override fun onAttach(scope: CoroutineScope) {
        scope.launch(ioDispatcher) {
            stateChannel.send(Loading)
            stateChannel.send(
                ShowingChatData(currentConversation.messages())
            )
        }
    }

    override fun onDetach() {
        stateChannel.close()
    }
}

sealed class ScreenState
object Loading : ScreenState()
data class ShowingChatData(val messages: List<Message>) : ScreenState()
data class ShowingError(val errorMessage: String) : ScreenState()
