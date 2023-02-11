package com.changan.module_main.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.changan.module_base.service.MAIN_SERVICE
import com.changan.module_base.service.MainService

@Route(path = MAIN_SERVICE, name = "MainServiceImpl")
class MainServiceImpl : MainService {
    override fun init(context: Context?) {

    }
}