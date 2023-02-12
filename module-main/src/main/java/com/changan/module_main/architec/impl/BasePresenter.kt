package com.changan.module_main.architec.impl

import android.content.Context
import android.util.Log
import com.changan.base.core.lifecycle.ActivityEvent
import com.changan.base.core.lifecycle.LifecycleProvider
import com.changan.module_main.architec.IPresenter
import com.changan.module_main.architec.IViewModel
import com.tantan.build.ui.app.Act
import io.reactivex.functions.Consumer

abstract class BasePresenter<out V : IViewModel<IPresenter<V>>>(protected var lifecycleProviderImpl: LifecycleProvider<*>) :
    IPresenter<V> {
    protected var viewModel: @UnsafeVariance V? = null
    override fun bindViewModel(viewModel: @UnsafeVariance V) {
        this.viewModel = viewModel
        this.viewModel!!.bindPresenter(this)
    }

    fun act(): Act? {
        return viewModel!!.act()
    }

    fun context(): Context? {
        return viewModel!!.context()
    }


    init {
        lifecycleProviderImpl.lifecycle().subscribe {

            val event = it as ActivityEvent
            if (event == ActivityEvent.DESTROY) {
                destroy()
            }
        }
    }
}