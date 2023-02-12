package com.changan.module_favorites.ui.tangram.view

import android.util.Log
import android.view.View
import com.tmall.wireless.tangram.structure.CellRender
import com.changan.module_favorites.ui.tangram.view.CustomCellView
import com.changan.module_favorites.ui.tangram.view.CustomExposureSupport
import com.tmall.wireless.tangram.dataparser.concrete.Card
import com.tmall.wireless.tangram.structure.BaseCell
import com.tmall.wireless.tangram.support.ExposureSupport

class CustomExposureSupport : ExposureSupport() {
    override fun onExposure(card: Card, offset: Int, position: Int) {
        Log.d(
            TAG,
            "onExposure: card=" + card.javaClass.simpleName + ", offset=" + offset + ", position=" + position
        )
    }

    override fun defaultExposureCell(targetView: View, cell: BaseCell<*>, type: Int) {
        Log.d(
            TAG,
            "defaultExposureCell: targetView=" + targetView.javaClass.simpleName + ", pos=" + cell.pos + ", type=" + type
        )
    }

    override fun defaultTrace(targetView: View, cell: BaseCell<*>, type: Int) {
        Log.d(
            TAG,
            "defaultTrace: targetView=" + targetView.javaClass.simpleName + ", pos=" + cell.pos + ", type=" + type
        )
    }

    companion object {
        private const val TAG = "CustomExposureSupport"
    }

    init {
        setOptimizedMode(true)
    }
}