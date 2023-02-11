package com.changan.module_base.service

import com.alibaba.android.arouter.launcher.ARouter
import com.changan.module_base.module.Module

object ServiceProvider {
    val favoritesService: FavoriteService =
        ARouter.getInstance().build(FAVORITES_SERVICE).navigation() as FavoriteService
    val mainService: MainService =
        ARouter.getInstance().build(MAIN_SERVICE).navigation() as MainService
    val listService: ListService =
        ARouter.getInstance().build(LIST_SERVICE).navigation() as ListService

}