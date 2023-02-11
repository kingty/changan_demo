package com.changan.base

import android.app.Application
import android.content.Context
import com.alibaba.android.arouter.launcher.ARouter

open class BaseApp : Application() {
    private val TAG = "BaseApp"
    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
    }

    override fun onCreate() {
        super.onCreate()

        ARouter.openLog();     // Print log
        ARouter.openDebug();   // Turn on debugging mode (If you are running in InstantRun mode, you must turn on debug mode! Online version needs to be closed, otherwise there is a security risk)
        ARouter.init(this)

    }
}