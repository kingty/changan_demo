package com.changan.module_main

import android.annotation.SuppressLint
import android.os.Bundle
import com.alibaba.android.arouter.launcher.ARouter
import com.changan.module_base.PATH_FAVORITE_ACT
import com.changan.module_base.PATH_LIST_ACT
import com.changan.module_base.service.ServiceProvider
import com.tantan.build.core.bindToLifecycle
import com.tantan.build.ui.app.Act
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Act() {
    @SuppressLint("CheckResult")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        to_list.setOnClickListener {
            ARouter.getInstance().build(PATH_LIST_ACT).withString("key", "value").navigation()
        }
        to_favorites.setOnClickListener {
            ARouter.getInstance().build(PATH_FAVORITE_ACT).withString("key", "value").navigation()
        }

        service_get.setOnClickListener {
            ServiceProvider.listService.getTime().bindToLifecycle(this).subscribe {
                time.text = it.toString()
            }
        }
    }
}