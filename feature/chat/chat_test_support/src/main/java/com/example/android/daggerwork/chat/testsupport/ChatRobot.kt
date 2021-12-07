package com.example.android.daggerwork.chat.testsupport

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import com.example.android.daggerwork.chat.R.id

fun onChat(func: ChatRobot.() -> Unit) = ChatRobot()
    .apply(func)

class ChatRobot {
    fun verify(screenTitle: String = "User") {
        onView(withText(screenTitle)).check(matches(isDisplayed()))
        onView(withId(id.messages)).check(matches(isDisplayed()))
        onView(withId(id.send_message_button)).check(matches(isDisplayed()))
        onView(withId(id.send_text)).check(matches(isDisplayed()))
    }

    fun tapButton() {
        onView(withId(id.send_message_button)).perform(click())
    }
}
