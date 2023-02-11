package com.changan.module_main

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.changan.module_base.module.MAIN_MODULE
import com.changan.module_base.module.Module

@Route(path = MAIN_MODULE, name = "MainModule")
class MainModule : Module {
    private val TAG = "MainModule"
    override fun afterOnCreate() {
        Log.d(TAG, "MainModule afterOnCreate")
    }

    override fun init(context: Context?) {
        Log.d(TAG, context.toString())
    }

}