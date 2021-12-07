package com.example.android.daggerwork.nointernet

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import android.widget.TextView
import com.example.android.daggerwork.nointernet.R.layout
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

private const val scopeName = "NoInternetView"

@SuppressLint("ViewConstructor")
class NoInternetView(context: Context, private val noInternetViewModel: NoInternetViewModel) : LinearLayout(context) {

    private val coroutineName = CoroutineName(scopeName)
    private val job = SupervisorJob()

    private val scope = CoroutineScope(
        Dispatchers.Main + job + coroutineName
    )

    init {
        LayoutInflater.from(context).inflate(layout.no_internet_layout, this)
        layoutParams = LayoutParams(MATCH_PARENT, MATCH_PARENT)
        orientation = VERTICAL
    }

    @InternalCoroutinesApi
    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        scope.launch {
            noInternetViewModel.screenData.collect {
                when (it) {
                    Loading -> Unit
                    is ShowingScreen -> showScreen(it)
                    is ShowingError -> Unit
                }
            }
        }
        noInternetViewModel.onAttach(scope)
    }

    private fun showScreen(state: ShowingScreen) {
        findViewById<TextView>(R.id.no_internet_text).text = state.noInternetMessage
    }

    override fun onDetachedFromWindow() {
        super.onDetachedFromWindow()
        scope.cancel()
    }
}
