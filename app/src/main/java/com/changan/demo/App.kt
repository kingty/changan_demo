package com.changan.demo

import android.app.Application
import android.content.Context
import com.changan.base.BaseApp
import com.changan.module_base.module.ModuleManager

class App : BaseApp() {
    private val TAG = "App"

    override fun onCreate() {
        super.onCreate()

        val manager = ModuleManager()
        manager.registerModules()
        manager.afterCreate()
    }

}