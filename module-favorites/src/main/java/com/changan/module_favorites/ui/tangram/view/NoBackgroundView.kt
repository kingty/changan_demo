package com.changan.module_favorites.ui.tangram.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.LinearLayout
import android.widget.TextView
import androidx.annotation.Nullable
import com.changan.module_favorites.ui.tangram.util.Utils
import com.tmall.wireless.tangram.structure.BaseCell
import com.tmall.wireless.tangram.structure.view.ITangramViewLifeCycle
import com.tmall.wireless.tangram.support.ExposureSupport
import java.util.*

class NoBackgroundView : LinearLayout, ITangramViewLifeCycle {
    private var mTextView: TextView? = null

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, @Nullable attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(
        context: Context?, @Nullable attrs: AttributeSet?,
        defStyleAttr: Int
    ) : super(context, attrs, defStyleAttr) {
        init()
    }

    private fun init() {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        val padding: Int = Utils.dip2px(context, 10F)
        setPadding(padding, padding, padding, padding)
        mTextView = TextView(context)
        mTextView!!.textSize = 12f
        addView(mTextView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    override fun cellInited(cell: BaseCell<*>) {
        setOnClickListener(cell)
        if (cell.serviceManager != null) {
            val exposureSupport = cell.serviceManager!!.getService(
                ExposureSupport::class.java
            )
            exposureSupport?.onTrace(this, cell, cell.type)
        }
    }

    override fun postBindView(cell: BaseCell<*>) {
        mTextView!!.text = String.format(
            Locale.CHINA, "%s%d: %s", javaClass.simpleName,
            cell.pos, cell.optParam("text")
        )
    }

    override fun postUnBindView(cell: BaseCell<*>?) {}
}