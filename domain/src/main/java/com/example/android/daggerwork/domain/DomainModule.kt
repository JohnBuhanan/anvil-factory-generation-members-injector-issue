package com.example.android.daggerwork.domain

import com.dropbox.android.external.store4.Fetcher
import com.dropbox.android.external.store4.StoreBuilder
import com.example.android.daggerwork.domain.features.CurrentConversation
import com.example.android.daggerwork.domain.features.CurrentConversationImpl
import com.example.android.daggerwork.domain.features.LoginFeature
import com.example.android.daggerwork.domain.features.LoginFeatureImpl
import com.example.android.daggerwork.domain.repositories.ConversationStore
import com.example.android.daggerwork.domain.repositories.LoginRequest
import com.example.android.daggerwork.domain.repositories.LoginResponse
import com.example.android.daggerwork.domain.repositories.LoginStore
import com.example.android.daggerwork.domain.repositories.conversations
import com.example.android.daggerwork.domain.repositories.login
import com.example.android.daggerwork.domain.session.LoggedInSourceOfTruth
import com.example.android.daggerwork.domain.session.LoggedInStatus
import com.example.android.daggerwork.domain.session.UserData
import com.example.android.daggerwork.domain.session.UserDataSourceOfTruth
import com.example.android.daggerwork.domain.session.UserDataStore
import com.example.android.daggerwork.domain.session.inMemorySourceOfTruth
import com.squareup.anvil.annotations.ContributesTo
import dagger.Module
import dagger.Provides
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import javax.inject.Singleton

@Module
@ContributesTo(Singleton::class)
@FlowPreview
@ExperimentalCoroutinesApi
object DomainModule {

    @[Provides Singleton]
    fun providesLoggedInSourceOfTruth(): LoggedInSourceOfTruth {
        return inMemorySourceOfTruth(default = LoggedInStatus(false))
    }

    @[Provides Singleton]
    fun providesUserDataSourceOfTruth(): UserDataSourceOfTruth {
        return inMemorySourceOfTruth(default = UserData(""))
    }

    @[Provides Singleton]
    internal fun provideLoginFeature(
        repo: LoginStore,
        userData: UserDataSourceOfTruth,
        loggedInSourceOfTruth: LoggedInSourceOfTruth,
    ): LoginFeature {
        return LoginFeatureImpl(repo, userData, loggedInSourceOfTruth)
    }

    @Provides
    internal fun provideCurrentConversation(
        userData: UserDataStore,
        conversationStore: ConversationStore,
    ): CurrentConversation {
        return CurrentConversationImpl(userData, conversationStore)
    }

    @[Provides Singleton]
    internal fun provideConversationStore(): ConversationStore {
        return StoreBuilder.from(
            Fetcher.of { userId: String -> conversations.getValue(userId) }
        ).build()
    }

    @[Provides Singleton]
    internal fun provideLoginStore(): LoginStore {
        return StoreBuilder.from<LoginRequest, LoginResponse>(
            Fetcher.of { request -> login(request.userId, request.password) },
        ).disableCache() // The source of truth is already in memory, we don't need an additional caching layer.
            .build()
    }

    @[Provides Singleton]
    internal fun provideUserDataStore(sourceOfTruth: UserDataSourceOfTruth): UserDataStore {
        return StoreBuilder.from(
            Fetcher.of { UserData("") },
            sourceOfTruth = sourceOfTruth
        ).disableCache() // The source of truth is already in memory, we don't need an additional caching layer.
            .build()
    }

}
