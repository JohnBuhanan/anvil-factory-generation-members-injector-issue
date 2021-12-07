package com.example.android.daggerwork.nointernet.runner

import androidx.test.core.app.launchActivity
import com.example.android.daggerwork.nointernet.NoInternetActivity
import com.example.android.daggerwork.nointernet.testsupport.onNoInternet
import org.junit.Before
import org.junit.Test

class NoInternetActivityTest {

    @Before
    fun setUp() {
        launchActivity<NoInternetActivity>()
    }

    @Test
    fun whenOpeningNoInternetScreen_verifyDefaultDataIsDisplayed() {
        onNoInternet {
            verify("No Internet")
        }
    }
}

