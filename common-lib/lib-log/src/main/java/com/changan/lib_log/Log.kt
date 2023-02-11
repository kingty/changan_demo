package com.changan.lib_log

import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger


//这里封装自己的logger
//这里demo只做最简单处理
object Log {

    init {
        Logger.addLogAdapter(AndroidLogAdapter())
    }

    fun d(message: String) {
        Logger.d(message)
    }

    fun e(message: String) {
        Logger.e(message)
    }
}