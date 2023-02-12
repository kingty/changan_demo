package com.changan.base.core.app

import android.content.Context
import android.os.Bundle
import androidx.annotation.CallSuper
import androidx.annotation.CheckResult
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import android.view.View
import com.changan.base.core.lifecycle.FragmentEvent
import com.changan.base.core.lifecycle.LifecycleProvider
import com.changan.base.core.lifecycle.LifecycleTransformer
import com.changan.base.core.lifecycle.RxLifecycle
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

open class Frag : androidx.fragment.app.Fragment(), LifecycleProvider<FragmentEvent> {

    private val lifecycleSubject = BehaviorSubject.create<FragmentEvent>()

    @NonNull
    @CheckResult
    override fun lifecycle(): Observable<FragmentEvent> {
        return lifecycleSubject.hide()
    }

    @NonNull
    @CheckResult
    override fun <T> bindUntilEvent(@NonNull event: FragmentEvent): LifecycleTransformer<T> {
        return RxLifecycle.bindUntilEvent(lifecycleSubject, event)
    }

    @NonNull
    @CheckResult
    override fun <T> bindToLifecycle(): LifecycleTransformer<T> {
        return RxLifecycle.bindFragment(lifecycleSubject)
    }

    @CallSuper
    override fun onAttach(context: Context) {
        super.onAttach(context)
        lifecycleSubject.onNext(FragmentEvent.ATTACH)
    }


    @CallSuper
    override fun onCreate(@Nullable savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleSubject.onNext(FragmentEvent.CREATE)
    }

    @CallSuper
    override fun onViewCreated(@NonNull view: View, @Nullable savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleSubject.onNext(FragmentEvent.CREATE_VIEW)
    }

    @CallSuper
    override fun onStart() {
        super.onStart()
        lifecycleSubject.onNext(FragmentEvent.START)
    }

    @CallSuper
    override fun onResume() {
        super.onResume()
        lifecycleSubject.onNext(FragmentEvent.RESUME)
    }

    @CallSuper
    override fun onPause() {
        lifecycleSubject.onNext(FragmentEvent.PAUSE)
        super.onPause()
    }

    @CallSuper
    override fun onStop() {
        lifecycleSubject.onNext(FragmentEvent.STOP)
        super.onStop()
    }

    @CallSuper
    override fun onDestroyView() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY_VIEW)
        super.onDestroyView()
    }

    @CallSuper
    override fun onDestroy() {
        lifecycleSubject.onNext(FragmentEvent.DESTROY)
        super.onDestroy()
    }

    @CallSuper
    override fun onDetach() {
        lifecycleSubject.onNext(FragmentEvent.DETACH)
        super.onDetach()
    }
}