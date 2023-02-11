package com.changan.module_base.module

import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.launcher.ARouter

open class ModuleManager {
    var modules: MutableList<Module> = mutableListOf()

    fun registerModules() {
        val moduleFavorites: Module =
            ARouter.getInstance().build(FAVORITES_MODULE).navigation() as Module
        val moduleMain: Module = ARouter.getInstance().build(MAIN_MODULE).navigation() as Module
        val moduleList: Module = ARouter.getInstance().build(LIST_MODULE).navigation() as Module
        modules.add(moduleFavorites)
        modules.add(moduleMain)
        modules.add(moduleList)
    }


    fun afterCreate() {
        for (module in modules) {
            module.afterOnCreate()
        }
    }

    fun onTrimMemory(level: Int) {
        for (module in modules) {
            module.onTrimMemory(level)
        }
    }

    fun onLowMemory() {
        for (module in modules) {
            module.onLowMemory()
        }
    }

    fun xxx() {
        for (module in modules) {
            module.xxx()
        }
    }
}