package com.example.android.daggerwork.domain.models

data class Labels(
    val chatLabels: ChatLabels = ChatLabels(),
    val noInternetLabels: NoInternetLabels = NoInternetLabels(),
)

data class ChatLabels(val chatScreenTitle: String = "Chat")

data class NoInternetLabels(
    val noInternetScreenTitle: String = "No Internet",
    val noInternetMessage: String = "There is no internet connection!",
)

