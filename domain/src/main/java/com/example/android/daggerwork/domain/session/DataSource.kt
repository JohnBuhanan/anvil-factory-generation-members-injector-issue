package com.example.android.daggerwork.domain.session

import com.dropbox.android.external.store4.SourceOfTruth
import com.dropbox.android.external.store4.Store

typealias UserDataStore = Store<Unit, UserData>

typealias LoggedInSourceOfTruth = SourceOfTruth<Unit, LoggedInStatus, LoggedInStatus>

typealias UserDataSourceOfTruth = SourceOfTruth<Unit, UserData, UserData>

data class LoggedInStatus(val status: Boolean)

data class UserData(val username: String?)
