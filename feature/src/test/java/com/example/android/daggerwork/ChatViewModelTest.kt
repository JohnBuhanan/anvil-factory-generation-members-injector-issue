package com.example.android.daggerwork

import com.example.android.daggerwork.chat.ChatViewModel
import com.example.android.daggerwork.chat.ChatViewModelImpl
import com.example.android.daggerwork.chat.Loading
import com.example.android.daggerwork.chat.ShowingChatData
import com.example.android.daggerwork.domain.models.Message.IncomingMessage
import com.example.android.daggerwork.domain.models.Message.OutgoingMessage
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@[FlowPreview ExperimentalCoroutinesApi]
class ChatViewModelTest {

    private val coroutineScope: CoroutineScope = TestCoroutineScope()
    private val testDispatcher = TestCoroutineDispatcher()
    private val viewModel: ChatViewModel = ChatViewModelImpl(
        testDispatcher,
        currentConversation = {
            listOf(
                IncomingMessage("Hello"),
                IncomingMessage("Do you like Dagger?"),
                OutgoingMessage("Hello, yes, definitely!"))
        }
    )

    @Before
    fun setUp() {
        viewModel.onAttach(coroutineScope)
    }

    @After
    fun cleanUp() {
        viewModel.onDetach()
    }

    @Test
    fun `when launch screen verify loading is shown`() = runBlocking {
        val result = viewModel.screenData.take(1).toList()
        assertEquals(Loading, result[0])
    }

    @Test
    fun `when launch screen and get messages verify messages are shown`() = runBlocking {
        val expected = ShowingChatData(
            listOf(
                IncomingMessage("Hello"),
                IncomingMessage("Do you like Dagger?"),
                OutgoingMessage("Hello, yes, definitely!")
            )
        )
        val result = viewModel.screenData.take(2).toList()
        assertEquals(expected, result[1])
    }

}
