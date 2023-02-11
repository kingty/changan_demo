package com.tantan.build.core.lifecycle

import android.view.View
import com.changan.base.core.verifyMainThread
import io.reactivex.ObservableEmitter
import io.reactivex.ObservableOnSubscribe
import io.reactivex.android.MainThreadDisposable


internal class ViewDetachesOnSubscribe(val view: View) : ObservableOnSubscribe<Any> {

    @Throws(Exception::class)
    override fun subscribe(emitter: ObservableEmitter<Any>) {
        verifyMainThread()
        val listener = EmitterListener(emitter)
        emitter.setDisposable(listener)
        view.addOnAttachStateChangeListener(listener)
    }

    internal inner class EmitterListener(val emitter: ObservableEmitter<Any>) :
        MainThreadDisposable(), View.OnAttachStateChangeListener {

        override fun onViewAttachedToWindow(view: View) {
            // Do nothing
        }

        override fun onViewDetachedFromWindow(view: View) {
            emitter.onNext(SIGNAL)
        }

        protected override fun onDispose() {
            view.removeOnAttachStateChangeListener(this)
        }
    }

    companion object {

        val SIGNAL = Any()
    }

}
