package com.changan.module_phonelist

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.changan.module_base.module.LIST_MODULE
import com.changan.module_base.module.Module

@Route(path = LIST_MODULE, name = "ListModule")
class ListModule : Module {
    private val TAG = "ListModule"
    override fun afterOnCreate() {
        Log.d(TAG, "ListModule afterOnCreate")
    }

    override fun init(context: Context?) {
        Log.d(TAG, context.toString())
    }
}