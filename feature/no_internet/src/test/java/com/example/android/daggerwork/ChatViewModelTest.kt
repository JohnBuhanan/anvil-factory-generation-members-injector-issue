package com.example.android.daggerwork

import com.example.android.daggerwork.nointernet.Loading
import com.example.android.daggerwork.nointernet.NoInternetViewModel
import com.example.android.daggerwork.nointernet.NoInternetViewModelImpl
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.TestCoroutineScope
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

@[FlowPreview ExperimentalCoroutinesApi]
class ChatViewModelTest {

    private val coroutineScope: CoroutineScope = TestCoroutineScope()
    private val testDispatcher = TestCoroutineDispatcher()
    private val viewModel: NoInternetViewModel =
        NoInternetViewModelImpl(testDispatcher, "No Internet")

    @Before
    fun setUp() {
        viewModel.onAttach(coroutineScope)
    }

    @After
    fun cleanUp() {
        viewModel.onDetach()
    }

    @Test
    fun `when launch screen verify loading is shown`() = runBlocking {
        val result = viewModel.screenData.take(1).toList()
        assertEquals(Loading, result[0])
    }
}
