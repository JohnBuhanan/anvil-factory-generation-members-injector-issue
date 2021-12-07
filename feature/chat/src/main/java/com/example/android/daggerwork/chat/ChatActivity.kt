package com.example.android.daggerwork.chat

import android.os.Bundle
import android.widget.FrameLayout
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.dropbox.android.external.store4.get
import com.example.android.dagger_support.InjectorProvider
import com.example.android.daggerwork.domain.features.CurrentConversation
import com.example.android.daggerwork.domain.models.ChatLabels
import com.example.android.daggerwork.domain.session.UserDataStore
import com.example.android.daggerwork.router.Router
import com.example.android.daggerwork.routes.Route
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ChatActivity : AppCompatActivity() {

    @Inject
    lateinit var labels: ChatLabels

    @Inject
    lateinit var userDataStore: UserDataStore

    @Inject
    lateinit var currentConversation: CurrentConversation

    @Inject
    lateinit var router: Router

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        (application as InjectorProvider).injector<ChatComponent>().inject(this)

        setContentView(R.layout.activity_chat)
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.CREATED) {
                supportActionBar?.title = userDataStore.get(Unit).username
            }
        }

        val chatViewModel: ChatViewModel = ChatViewModelImpl(Dispatchers.IO, currentConversation)

        findViewById<FrameLayout>(R.id.screen_container).addView(
            ChatView(context = baseContext, chatViewModel = chatViewModel)
        )

        findViewById<ImageButton>(R.id.send_message_button).setOnClickListener {
            router.goTo(this, Route.NoInternet)
        }
    }
}
