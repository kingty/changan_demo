package com.tantan.build.ui.app

import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.CheckResult
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import com.changan.base.core.lifecycle.ActivityEvent
import com.changan.base.core.lifecycle.LifecycleProvider
import com.changan.base.core.lifecycle.LifecycleTransformer
import com.changan.base.core.lifecycle.RxLifecycle
import com.changan.base.core.verifyMainThread
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

open class Act : AppCompatActivity(), LifecycleProvider<ActivityEvent> {

    private val lifecycleSubject = BehaviorSubject.create<ActivityEvent>()

    @CheckResult
    override fun lifecycle(): Observable<ActivityEvent> {
        return lifecycleSubject.hide()
    }

    @CheckResult
    override fun <T> bindUntilEvent(event: ActivityEvent): LifecycleTransformer<T> {
        return RxLifecycle.bindUntilEvent<T, ActivityEvent>(lifecycleSubject, event)
    }

    @CheckResult
    override fun <T> bindToLifecycle(): LifecycleTransformer<T> {
        return RxLifecycle.bindActivity(lifecycleSubject)
    }


    @CallSuper
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleSubject.onNext(ActivityEvent.BEFORE_CREATE)
        lifecycleSubject.onNext(ActivityEvent.AFTER_CREATE)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        lifecycleSubject.onNext(ActivityEvent.START)
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        lifecycleSubject.onNext(ActivityEvent.RESUME)
    }

    @CallSuper
    override fun onPause() {
        lifecycleSubject.onNext(ActivityEvent.PAUSE)
        super.onPause()
    }

    @CallSuper
    override fun onStop() {
        lifecycleSubject.onNext(ActivityEvent.STOP)
        super.onStop()
    }

    @CallSuper
    override fun onDestroy() {
        lifecycleSubject.onNext(ActivityEvent.DESTROY)
        super.onDestroy()
    }
}


