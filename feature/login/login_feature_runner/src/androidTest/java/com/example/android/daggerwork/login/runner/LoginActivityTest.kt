package com.example.android.daggerwork.login.runner

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.performClick
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.android.daggerwork.login.LoginActivity
import com.example.android.daggerwork.login.LoginScreen
import org.junit.Ignore
import org.junit.Rule
import org.junit.Test

/**
 * TODO: This test exposes a deficiency in the current setup and should be Ignored in the meantime.
 *
 * When the Login button is clicked, the real router attempts to resolve the FQDN in the Route, but can't
 * find it because Login doesn't (and shouldn't) depend on project(:feature:chat).
 *
 * We probably should stub the Route with some kind of assertable Router, similar to the Intent Stubbing API.
 *
 * e.g.  router.assertThat(Chat()).wasStarted()
 */
class LoginActivityTest {

    @get:Rule
    val composeTestRule = createAndroidComposeRule<LoginActivity>()

    @Test
    @Ignore("ChatActivity is not known by this module")
    fun whenLaunchingTheApp_weCanLogin() {

        // Start the app
        composeTestRule.setContent {
            LoginScreen(viewModel())
        }

        composeTestRule.onNodeWithTag("LoginButton").performClick()
    }
}
