package com.changan.module_phonelist.logic

import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import java.util.concurrent.TimeUnit

object TimeProvider {

    fun now(): Observable<Long> {
        //模拟业务
        return Observable.just(System.nanoTime())
    }
}