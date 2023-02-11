package com.changan.base.core.lifecycle

import androidx.annotation.CheckResult
import androidx.annotation.NonNull
import android.view.View
import com.changan.base.core.checkNotNull
import com.tantan.build.core.lifecycle.ViewDetachesOnSubscribe
import io.reactivex.Observable
import io.reactivex.annotations.CheckReturnValue
import io.reactivex.exceptions.Exceptions
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.functions.Predicate


class RxLifecycle private constructor() {

    init {
        throw AssertionError("No instances")
    }

    companion object {


        @CheckReturnValue
        fun <T, R> bindUntilEvent(lifecycle: Observable<R>,
                                  event: R): LifecycleTransformer<T> {
            checkNotNull(lifecycle, "lifecycle == null")
            checkNotNull(event, "event == null")
            return bind(takeUntilEvent(lifecycle, event))
        }

        private fun <R> takeUntilEvent(lifecycle: Observable<R>, event: R): Observable<R> {
            return lifecycle.filter { lifecycleEvent -> lifecycleEvent == event }
        }


        @CheckReturnValue
        fun <T, R> bind(lifecycle: Observable<R>): LifecycleTransformer<T> {
            return LifecycleTransformer(lifecycle)
        }


        @CheckReturnValue
        fun <T, R> bind(lifecycle: Observable<R>,
                        correspondingEvents: Function<R, R>): LifecycleTransformer<T> {
            checkNotNull(lifecycle, "lifecycle == null")
            checkNotNull(correspondingEvents, "correspondingEvents == null")
            return bind(takeUntilCorrespondingEvent(lifecycle.share(), correspondingEvents))
        }

        private fun <R> takeUntilCorrespondingEvent(lifecycle: Observable<R>,
                                                    correspondingEvents: Function<R, R>): Observable<Boolean> {
            return Observable.combineLatest(
                    lifecycle.take(1).map(correspondingEvents),
                    lifecycle.skip(1),
                    BiFunction<R, R, Boolean> { bindUntilEvent, lifecycleEvent -> lifecycleEvent == bindUntilEvent })
                    .onErrorReturn(RESUME_FUNCTION)
                    .filter(SHOULD_COMPLETE)
        }

        private val RESUME_FUNCTION: Function<Throwable, Boolean> = Function { throwable ->
            if (throwable is OutsideLifecycleException) {
                return@Function true
            }


            Exceptions.propagate(throwable)
            false
        }


        private val SHOULD_COMPLETE: Predicate<Boolean> = Predicate { t -> t }


        @NonNull
        @CheckResult
        fun <T> bindActivity(@NonNull lifecycle: Observable<ActivityEvent>): LifecycleTransformer<T> {
            return bind(lifecycle, ACTIVITY_LIFECYCLE)
        }


        @NonNull
        @CheckResult
        fun <T> bindFragment(@NonNull lifecycle: Observable<FragmentEvent>): LifecycleTransformer<T> {
            return bind(lifecycle, FRAGMENT_LIFECYCLE)
        }


        @NonNull
        @CheckResult
        fun <T> bindView(@NonNull view: View): LifecycleTransformer<T> {
            checkNotNull(view, "view == null")

            return bind(Observable.create(ViewDetachesOnSubscribe(view)))
        }

        // Figures out which corresponding next lifecycle event in which to unsubscribe, for Activities
        val ACTIVITY_LIFECYCLE = Function<ActivityEvent, ActivityEvent> { lastEvent ->
            when (lastEvent) {
                ActivityEvent.BEFORE_CREATE -> ActivityEvent.DESTROY
                ActivityEvent.AFTER_CREATE -> ActivityEvent.DESTROY
                ActivityEvent.START -> ActivityEvent.STOP
                ActivityEvent.RESUME -> ActivityEvent.PAUSE
                ActivityEvent.PAUSE -> ActivityEvent.STOP
                ActivityEvent.STOP -> ActivityEvent.DESTROY
                ActivityEvent.DESTROY -> throw OutsideLifecycleException("Cannot bind to Activity lifecycle when outside of it.")
                else -> throw UnsupportedOperationException("Binding to $lastEvent not yet implemented")
            }
        }

        // Figures out which corresponding next lifecycle event in which to unsubscribe, for Fragments
        val FRAGMENT_LIFECYCLE = Function<FragmentEvent, FragmentEvent> { lastEvent ->
            when (lastEvent) {
                FragmentEvent.ATTACH -> FragmentEvent.DETACH
                FragmentEvent.CREATE -> FragmentEvent.DESTROY
                FragmentEvent.CREATE_VIEW -> FragmentEvent.DESTROY_VIEW
                FragmentEvent.START -> FragmentEvent.STOP
                FragmentEvent.RESUME -> FragmentEvent.PAUSE
                FragmentEvent.PAUSE -> FragmentEvent.STOP
                FragmentEvent.STOP -> FragmentEvent.DESTROY_VIEW
                FragmentEvent.DESTROY_VIEW -> FragmentEvent.DESTROY
                FragmentEvent.DESTROY -> FragmentEvent.DETACH
                FragmentEvent.DETACH -> throw OutsideLifecycleException("Cannot bind to Fragment lifecycle when outside of it.")
                else -> throw UnsupportedOperationException("Binding to $lastEvent not yet implemented")
            }
        }
    }
}


