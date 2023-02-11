package com.changan.module_phonelist.logic

import com.changan.module_phonelist.data.Item
import com.changan.module_phonelist.db.LocalFetcher
import com.changan.module_phonelist.network.RemoteFetcher
import com.tantan.build.core.itm
import io.reactivex.Observable
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.subjects.PublishSubject
import java.util.concurrent.TimeUnit

class ItemProvider {

    private val remoteFetcher = RemoteFetcher()
    private val localFetcher = LocalFetcher()
    private val p: PublishSubject<List<Item>> = PublishSubject.create()
    fun getItems(): Observable<List<Item>> {
        //先显示本地的
        localFetcher.getItemFromLocal().itm().subscribe {
            p.onNext(it)
        }
        //再显示远程加载的
        remoteFetcher.refreshItemFromRemote().itm().subscribeBy(

            onNext = {
                p.onNext(mockItemList())

            },
            onComplete = {

            },
            onError = {
                Observable.just(1).delay(5, TimeUnit.SECONDS).itm().subscribe {
                    p.onNext(mockItemList()) // 因为网络是模拟的，请求肯定失败，这里给一个mock数据
                }

            }
        )
        return p.hide()
    }

    private fun mockItemList(): List<Item> {
        return sequenceOf(
            Item(id = "1", name = "oppo"),
            Item(id = "2", name = "vivo"),
            Item(id = "3", name = "huawei"),
            Item(id = "4", name = "iphone"),
            Item(id = "5", name = "xiaomi"),
            Item(id = "6", name = "meizu"),
            Item(id = "7", name = "honor")
        ).toList()
    }
}