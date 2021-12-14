package com.example.android.daggerwork.common

sealed class Base {
    sealed class Error : Base() {
        object Thing : Error()
    }
}
