package com.changan.base.core.lifecycle

import io.reactivex.Observable
import io.reactivex.annotations.CheckReturnValue


interface LifecycleProvider<E> {
    /**
     * @return a sequence of lifecycle events
     */
    @CheckReturnValue
    fun lifecycle(): Observable<E>

    /**
     * Binds a source until a specific event occurs.
     *
     * @param event the event that triggers unsubscription
     * @return a reusable [LifecycleTransformer] which unsubscribes when the event triggers.
     */
    @CheckReturnValue
    fun <T> bindUntilEvent(event: E): LifecycleTransformer<T>

    /**
     * Binds a source until the next reasonable event occurs.
     *
     * @return a reusable [LifecycleTransformer] which unsubscribes at the correct time.
     */
    @CheckReturnValue
    fun <T> bindToLifecycle(): LifecycleTransformer<T>
}