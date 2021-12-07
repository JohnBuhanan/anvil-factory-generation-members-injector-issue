package com.example.android.daggerwork.nointernet.testsupport

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.ViewInteraction
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText

fun onNoInternet(func: NoInternetRobot.() -> Unit) = NoInternetRobot()
    .apply(func)

class NoInternetRobot {
    fun verify(screenTitle: String): ViewInteraction =
        onView(withText(screenTitle)).check(matches(isDisplayed()))
}
