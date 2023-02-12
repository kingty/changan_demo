package com.changan.module_favorites.ui.tangram.view

import android.view.View
import android.widget.Toast
import com.tmall.wireless.tangram.structure.CellRender
import com.changan.module_favorites.ui.tangram.view.CustomCellView
import com.changan.module_favorites.ui.tangram.view.CustomExposureSupport
import com.tmall.wireless.tangram.structure.BaseCell
import com.tmall.wireless.tangram.support.SimpleClickSupport

class CustomClickSupport : SimpleClickSupport() {
    override fun defaultClick(targetView: View, cell: BaseCell<*>, eventType: Int) {
        Toast.makeText(
            targetView.context,
            "您点击了组件，type=" + cell.stringType + ", pos=" + cell.pos, Toast.LENGTH_SHORT
        ).show()
    }

    init {
        setOptimizedMode(true)
    }
}