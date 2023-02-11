package com.changan.module_phonelist.db

import com.changan.module_phonelist.data.Item
import io.reactivex.Observable

class LocalFetcher {

    //这里模拟取数据库
    fun getItemFromLocal(): Observable<List<Item>> {
        return Observable.just(mockItemList())
    }

    private fun mockItemList(): List<Item> {
        return sequenceOf(
            Item(id = "1", name = "oppo local"),
            Item(id = "2", name = "vivo local"),
            Item(id = "3", name = "huawei local"),
            Item(id = "4", name = "iphone local"),
            Item(id = "5", name = "xiaomi local"),
            Item(id = "6", name = "meizu local"),
            Item(id = "7", name = "honor local")
        ).toList()
    }
}