package com.changan.module_favorites

import android.content.Context
import android.util.Log
import com.alibaba.android.arouter.facade.annotation.Route
import com.changan.module_base.module.FAVORITES_MODULE
import com.changan.module_base.module.Module

@Route(path = FAVORITES_MODULE, name = "FavoritesModule")
class FavoritesModule : Module {
    private val TAG = "FavoritesModule"
    override fun afterOnCreate() {
        Log.d(TAG, "FavoritesModule afterOnCreate")
    }

    override fun init(context: Context?) {
        Log.d(TAG, context.toString())
    }
}