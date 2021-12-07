package com.example.android.daggerwork.router

import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withText
import assertk.assertThat
import assertk.assertions.isEqualTo
import com.example.android.daggerwork.routes.Route
import org.junit.Assert.fail
import org.junit.Test

class RouterTest {

    @Test
    fun whenNavigatingToAnExistingRoute_navigateAndPassTheRouteAsAnIntentParam() {
        val scenario = launchActivity<RouterTestActivity>()
        val route = RouterTestRoute("hello world")

        scenario.onActivity {
            val router = RouterImpl()
            router.goTo(it, route)
        }

        onView(withText("hello world")).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun whenNavigatingToANonExistingRoute_throwRouteNotFoundException() {
        val scenario = launchActivity<RouterTestActivity>()

        scenario.onActivity {
            val router = RouterImpl()
            val route = object : Route("com.example.android.daggerwork.router.ActivityThatDoesNotExist") {}
            try {
                router.goTo(it, route)
                fail("Expected exception")
            } catch (e: RouteNotFound) {
                //expected
                assertThat(e.route).isEqualTo(route)
            }

        }
    }
}
