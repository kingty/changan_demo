package com.tantan.build.core

import android.view.View
import com.changan.base.core.lifecycle.LifecycleProvider
import com.changan.base.core.lifecycle.RxLifecycle
import io.reactivex.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


fun <T> Observable<T>.itm() =
    this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())

fun <T> Single<T>.itm() =
    this.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())


// RxLifecycle extensionsT

fun <T, E> Observable<T>.bindToLifecycle(provider: LifecycleProvider<E>): Observable<T> =
    this.compose<T>(provider.bindToLifecycle<T>())

fun <T, E> Observable<T>.bindUntilEvent(provider: LifecycleProvider<E>, event: E): Observable<T> =
    this.compose<T>(provider.bindUntilEvent(event))

fun <T> Observable<T>.bindToLifecycle(view: View): Observable<T> =
    this.compose<T>(RxLifecycle.bindView(view))

fun <T, E> Flowable<T>.bindToLifecycle(provider: LifecycleProvider<E>): Flowable<T> =
    this.compose<T>(provider.bindToLifecycle<T>())

fun <T, E> Flowable<T>.bindUntilEvent(provider: LifecycleProvider<E>, event: E): Flowable<T> =
    this.compose<T>(provider.bindUntilEvent(event))

fun <T> Flowable<T>.bindToLifecycle(view: View): Flowable<T> =
    this.compose<T>(RxLifecycle.bindView(view))

fun <T, E> Single<T>.bindToLifecycle(provider: LifecycleProvider<E>): Single<T> =
    this.compose(provider.bindToLifecycle<T>())

fun <T, E> Single<T>.bindUntilEvent(provider: LifecycleProvider<E>, event: E): Single<T> =
    this.compose(provider.bindUntilEvent<T>(event))

fun <T> Single<T>.bindToLifecycle(view: View): Single<T> =
    this.compose(RxLifecycle.bindView<T>(view))

fun <T, E> Maybe<T>.bindToLifecycle(provider: LifecycleProvider<E>): Maybe<T> =
    this.compose(provider.bindToLifecycle<T>())

fun <T, E> Maybe<T>.bindUntilEvent(provider: LifecycleProvider<E>, event: E): Maybe<T> =
    this.compose(provider.bindUntilEvent<T>(event))

fun <T> Maybe<T>.bindToLifecycle(view: View): Maybe<T> = this.compose(RxLifecycle.bindView<T>(view))

fun <E> Completable.bindToLifecycle(provider: LifecycleProvider<E>): Completable =
    this.compose(provider.bindToLifecycle<Completable>())

fun <E> Completable.bindUntilEvent(provider: LifecycleProvider<E>, event: E): Completable =
    this.compose(provider.bindUntilEvent<Completable>(event))

fun Completable.bindToLifecycle(view: View): Completable =
    this.compose(RxLifecycle.bindView<Completable>(view))