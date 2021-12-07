package com.example.android.daggerwork.login.testsupport

import androidx.compose.ui.test.SemanticsNodeInteractionsProvider
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextReplacement
import com.example.android.daggerwork.chat.testsupport.ChatRobot

fun onLogin(rule: SemanticsNodeInteractionsProvider, func: LoginRobot.() -> Unit) = LoginRobot(rule)
    .apply(func)

class LoginRobot(private val rule: SemanticsNodeInteractionsProvider) {

    fun setUserName(username: String) {
        rule.onNodeWithTag("UserId").performTextReplacement(username)
    }

    fun verify() {
        rule.onNodeWithTag("UserId").assertExists("Could not find UserId field")
    }

    infix fun pressLogin(func: ChatRobot.() -> Unit): ChatRobot {
        rule.onNodeWithTag("LoginButton").performClick()

        return ChatRobot().apply(func)
    }
}
