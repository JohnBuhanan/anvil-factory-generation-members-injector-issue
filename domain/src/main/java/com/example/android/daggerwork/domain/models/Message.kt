package com.example.android.daggerwork.domain.models

sealed class Message {
    abstract val text: String

    data class OutgoingMessage(override val text: String) : Message()
    data class IncomingMessage(override val text: String) : Message()
}
