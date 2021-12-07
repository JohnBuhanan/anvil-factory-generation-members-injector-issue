package com.example.android.daggerwork.chat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.Adapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.android.daggerwork.chat.MessageAdapter.MessageViewHolder
import com.example.android.daggerwork.domain.models.Message
import com.example.android.daggerwork.domain.models.Message.IncomingMessage
import com.example.android.daggerwork.domain.models.Message.OutgoingMessage

private const val INCOMING_MESSAGE = 0
private const val OUTGOING_MESSAGE = 1

class MessageAdapter(
    private val messages: List<Message>,
) : Adapter<MessageViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MessageViewHolder =
        MessageViewHolder(
            LayoutInflater.from(parent.context).inflate(
                when (viewType) {
                    INCOMING_MESSAGE -> R.layout.incoming_message
                    else -> R.layout.outgoing_message
                }, parent, false
            )
        )

    override fun getItemCount(): Int = messages.size

    override fun getItemViewType(position: Int) =
        when (messages[position]) {
            is IncomingMessage -> INCOMING_MESSAGE
            is OutgoingMessage -> OUTGOING_MESSAGE
        }

    override fun onBindViewHolder(holder: MessageViewHolder, position: Int) {
        holder.message.text = messages[position].text
    }

    class MessageViewHolder(view: View) : ViewHolder(view) {
        val message: TextView = view.findViewById(R.id.message_text)
    }

}

