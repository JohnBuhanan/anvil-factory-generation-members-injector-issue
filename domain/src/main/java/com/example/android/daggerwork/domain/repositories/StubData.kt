package com.example.android.daggerwork.domain.repositories

import com.example.android.daggerwork.domain.models.Message.IncomingMessage
import com.example.android.daggerwork.domain.models.Message.OutgoingMessage

/**
 * Represents the data that we could get back from a login call.  Expand as necessary.
 */
internal data class LoginRecord(val userId: String, val password: String)

internal val loginRecords = listOf(
    LoginRecord(userId = "User", password = "qwerty123"),
    LoginRecord(userId = "Steve234", password = "qwerty123"),
    LoginRecord(userId = "Bilbo", password = "qwerty123"),
    LoginRecord(userId = "FirstUser", password = "qwerty123"), //TODO: this is only here to make a test pass
    LoginRecord(userId = "SecondUser", password = "qwerty123") //TODO: this is only here to make a test pass
)

/**
 * These "Conversations" are intentionally static, but could be updated in a future commit
 */
internal val conversations: Map<String, Conversation> = mapOf(
    "User" to Conversation(listOf(
        IncomingMessage("Hello"),
        IncomingMessage("Do you like Dagger?"),
        OutgoingMessage("Hello, yes, definitely!")
    )),
    "Steve234" to Conversation(listOf(
        IncomingMessage("Hello, Steve"),
        IncomingMessage("Do you know about Hilt?"),
        OutgoingMessage("Yes, everybody does!")
    )),
    "Bilbo" to Conversation(listOf(
        IncomingMessage("Hello Bilbo"),
        IncomingMessage("Do you like Hephaestus?"),
        OutgoingMessage("Isn't it called Anvil now?")
    )),
).withDefault { Conversation(emptyList()) }
