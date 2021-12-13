package com.example.android.daggerwork.common.ui

class SomeException : IllegalStateException {
    constructor(message: String, cause: Throwable) : super(message, cause)

    constructor(message: String) : super(message)

    constructor(cause: Throwable) : super(cause)
}
