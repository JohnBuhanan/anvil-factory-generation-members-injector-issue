package com.example.android.daggerwork.chat

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val scopeName = "ChatView"

@SuppressLint("ViewConstructor")
class ChatView(context: Context, private val chatViewModel: ChatViewModel) : LinearLayout(context) {

    private val coroutineName = CoroutineName(scopeName)
    private val job = SupervisorJob()

    private val scope = CoroutineScope(
        Dispatchers.Main + job + coroutineName
    )

    init {
        LayoutInflater.from(context).inflate(R.layout.chat_layout, this)
        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        orientation = VERTICAL
    }

    @InternalCoroutinesApi
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        scope.launch {
            chatViewModel.screenData.collect {
                when (it) {
                    Loading -> Unit
                    is ShowingChatData -> renderListOfMessages(it)
                    is ShowingError -> Unit
                }
            }
        }
        chatViewModel.onAttach(scope)
    }

    private fun renderListOfMessages(state: ShowingChatData) {
        val messages = findViewById<RecyclerView>(R.id.messages)
        messages.layoutManager = LinearLayoutManager(context)
        messages.adapter = MessageAdapter(state.messages)
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        scope.cancel()
    }
}
