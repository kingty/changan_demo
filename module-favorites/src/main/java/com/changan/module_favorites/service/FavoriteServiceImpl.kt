package com.changan.module_favorites.service

import android.content.Context
import com.alibaba.android.arouter.facade.annotation.Route
import com.changan.module_base.module.FAVORITES_MODULE
import com.changan.module_base.service.FAVORITES_SERVICE
import com.changan.module_base.service.FavoriteService

@Route(path = FAVORITES_SERVICE, name = "FavoriteServiceImpl")
class FavoriteServiceImpl : FavoriteService {
    override fun init(context: Context?) {

    }
}