package com.changan.module_phonelist.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.changan.base.core.app.Frag
import com.changan.module_base.service.LIST_SERVICE
import com.changan.module_base.service.ListService
import com.changan.module_phonelist.logic.TimeProvider
import io.reactivex.Observable
import io.reactivex.Observer

@Route(path = LIST_SERVICE, name = "ListServiceImpl")

class ListServiceImpl : ListService {
    override fun getTime(): Observable<Long> {
        return TimeProvider.now()
    }

    override fun getFragment(): Frag? {
        return null
    }

    override fun init(context: Context?) {}
}