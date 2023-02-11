package com.changan.module_base.module

import com.alibaba.android.arouter.facade.template.IProvider

//管理module生命周期
//跟application生命周期挂钩
//或者跟其他业务逻辑生命周期挂钩，例如登陆后，退出后
interface Module : IProvider{
    // after application oncreate
    fun afterOnCreate(){}

    fun onTrimMemory(level: Int) {}
    fun onLowMemory() {}

    //
    fun xxx(){}
}