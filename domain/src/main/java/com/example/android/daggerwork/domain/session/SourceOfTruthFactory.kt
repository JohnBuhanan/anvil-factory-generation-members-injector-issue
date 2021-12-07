package com.example.android.daggerwork.domain.session

import com.dropbox.android.external.store4.SourceOfTruth
import java.util.concurrent.atomic.AtomicReference

fun <K : Any, I : Any, O : Any> inMemorySourceOfTruth(default: O, func: (I) -> O): SourceOfTruth<K, I, O> {
    val ref = AtomicReference<O>()
    return SourceOfTruth.of(
        nonFlowReader = { ref.get() ?: default },
        writer = { _: K, input: I -> ref.set(func(input)) }
    )
}

fun <K : Any, O : Any> inMemorySourceOfTruth(default: O): SourceOfTruth<K, O, O> {
    return inMemorySourceOfTruth(default) { it }
}
