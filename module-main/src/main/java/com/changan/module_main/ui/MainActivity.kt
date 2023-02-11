package com.changan.module_main.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import com.alibaba.android.arouter.launcher.ARouter
import com.changan.module_base.PATH_FAVORITE_ACT
import com.changan.module_base.PATH_LIST_ACT
import com.changan.module_base.service.ServiceProvider
import com.changan.module_main.R
import com.tantan.build.core.bindToLifecycle
import com.tantan.build.ui.app.Act
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : Act() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val presenter = MainPresenter(this)
        val viewModel = MainViewModel()
        presenter.bindViewModel(viewModel)

        setContentView(viewModel.inflateView(LayoutInflater.from(this), null))

    }
}