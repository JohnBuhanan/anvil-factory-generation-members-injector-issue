package com.example.android.daggerwork.chat.runner

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import com.example.android.dagger_support.InjectorProvider
import com.example.android.daggerwork.chat.ChatActivity
import com.example.android.daggerwork.chat.testsupport.onChat
import com.example.android.daggerwork.domain.session.UserData
import com.example.android.daggerwork.domain.session.UserDataSourceOfTruth
import com.example.android.daggerwork.featurerunner.TestLaunchRouterImpl
import com.example.android.daggerwork.featurerunner.di.LabelsOverrider
import com.example.android.daggerwork.routes.Route.ChatRoute.Chat2
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import javax.inject.Inject

@RunWith(AndroidJUnit4::class)
class ChatActivityTest {
    @Inject
    lateinit var labelsOverrider: LabelsOverrider

    @Inject
    lateinit var userData: UserDataSourceOfTruth

    @Before
    fun setUp() {
        (InstrumentationRegistry.getInstrumentation().targetContext.applicationContext as InjectorProvider).injector<TestChatRunnerComponent>()
            .inject(this)

        // TODO: There's probably a better way to structure this test, but the runBlocking is just there to keep the same semantics as before
        // the refactor to Store
        runBlocking { userData.write(Unit, UserData("Title for Test")) }

        val chatLabels = labelsOverrider.labels.chatLabels.copy(chatScreenTitle = "Title for Test")
        labelsOverrider.labels = labelsOverrider.labels.copy(chatLabels = chatLabels)

        TestLaunchRouterImpl.goToTest<ChatActivity>(Chat2(listOf("THIS IS A TEST")))
    }

    @Test
    fun whenOpeningTheChatScreen_verifyDefaultDataIsDisplayed() {
        onChat {
            verify(
                screenTitle = "Title for Test"
            )
        }
    }
}
