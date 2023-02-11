package com.changan.module_base.service

import com.alibaba.android.arouter.facade.template.IProvider
import com.changan.base.core.app.Frag
import io.reactivex.Observable
import io.reactivex.Observer

//通过接口下沉的方式来提供组件之间的相互调用和数据传递，以及提供组件的fragment
interface ListService : IProvider {

    fun getTime(): Observable<Long>
    fun getFragment(): Frag?
}