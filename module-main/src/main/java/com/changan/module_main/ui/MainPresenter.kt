package com.changan.module_main.ui

import com.alibaba.android.arouter.launcher.ARouter
import com.changan.base.core.lifecycle.LifecycleProvider
import com.changan.module_base.PATH_FAVORITE_ACT
import com.changan.module_base.PATH_LIST_ACT
import com.changan.module_base.service.ServiceProvider
import com.changan.module_main.architec.impl.BasePresenter
import com.tantan.build.core.bindToLifecycle
import kotlinx.android.synthetic.main.activity_main.*

class MainPresenter(lifecycleProviderImpl: LifecycleProvider<*>) : BasePresenter<MainViewModel>(
    lifecycleProviderImpl
) {
    override fun destroy() {

    }

    fun toListAct() {
        ARouter.getInstance().build(PATH_LIST_ACT).withString("key", "value").navigation()
    }

    fun toFavoriteAct() {
        ARouter.getInstance().build(PATH_FAVORITE_ACT).withString("key", "value").navigation()
    }

    fun getListServiceTime() {
        ServiceProvider.listService.getTime().bindToLifecycle(lifecycleProviderImpl).subscribe {
            viewModel?.setTimeText(it)
        }
    }
}