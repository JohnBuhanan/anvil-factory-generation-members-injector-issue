package com.example.android.daggerwork.nointernet

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.android.daggerwork.domain.models.NoInternetLabels
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject

class NoInternetActivity : AppCompatActivity() {

    @Inject
    lateinit var labels: NoInternetLabels

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        injector().inject(this)

        setContentView(R.layout.activity_no_internet)
        supportActionBar?.title = labels.noInternetScreenTitle

        val noInternetViewModel: NoInternetViewModel = NoInternetViewModelImpl(Dispatchers.IO, labels.noInternetMessage)
        findViewById<FrameLayout>(R.id.screen_container).addView(
            NoInternetView(context = this, noInternetViewModel = noInternetViewModel)
        )
    }
}
