package com.changan.base.core

import android.os.Looper


inline fun <T> checkNotNull(value: T?, message: String): T {
    if (value == null) {
        throw NullPointerException(message)
    }
    return value
}


inline fun verifyMainThread() {
    if (Looper.myLooper() != Looper.getMainLooper()) {
        throw IllegalStateException(
            "Expected to be called on the main thread but was " + Thread.currentThread().name
        )
    }
}