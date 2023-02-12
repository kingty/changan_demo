package com.changan.module_favorites.ui.tangram.view

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.changan.module_favorites.ui.tangram.util.Utils

class CustomCellView : LinearLayout {
    private var mImageView: ImageView? = null
    private var mTextView: TextView? = null

    constructor(context: Context?) : super(context) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }

    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ) {
        init()
    }

    private fun init() {
        orientation = VERTICAL
        gravity = Gravity.CENTER
        val padding = Utils.dip2px(context, 10f)
        setPadding(padding, padding, padding, padding)
        mImageView = ImageView(context)
        addView(
            mImageView, Utils.dip2px(context, 110f), Utils.dip2px(
                context, 72f
            )
        )
        mTextView = TextView(context)
        mTextView!!.setPadding(0, padding, 0, 0)
        addView(mTextView, LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    }

    fun setImageUrl(url: String?) {
//        Glide.with(this).load(url).into(mImageView);
    }

    fun setText(text: String?) {
        mTextView!!.text = text
    }
}