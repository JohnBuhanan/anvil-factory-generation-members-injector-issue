package com.example.android.daggerwork.nointernet

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

interface NoInternetViewModel {
    val screenData: Flow<ScreenState>
    fun onAttach(scope: CoroutineScope)
    fun onDetach()
}

class NoInternetViewModelImpl(private val ioDispatcher: CoroutineDispatcher, private val noInternetMessage: String) :
    NoInternetViewModel {

    private val stateChannel = Channel<ScreenState>()

    override val screenData: Flow<ScreenState>
        get() = stateChannel.consumeAsFlow()

    override fun onAttach(scope: CoroutineScope) {
        scope.launch(ioDispatcher) {
            stateChannel.send(Loading)
            stateChannel.send(
                ShowingScreen(noInternetMessage)
            )
        }
    }

    override fun onDetach() {
        stateChannel.close()
    }
}

sealed class ScreenState
object Loading : ScreenState()
data class ShowingScreen(val noInternetMessage: String) : ScreenState()
data class ShowingError(val errorMessage: String) : ScreenState()
