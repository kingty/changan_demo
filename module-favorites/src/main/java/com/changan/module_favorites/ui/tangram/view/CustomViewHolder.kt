package com.changan.module_favorites.ui.tangram.view

import android.content.Context
import android.view.View
import android.widget.TextView
import com.tmall.wireless.tangram.structure.viewcreator.ViewHolderCreator

class CustomViewHolder(context: Context?) : ViewHolderCreator.ViewHolder(context) {
    var textView: TextView? = null
    override fun onRootViewCreated(view: View) {
        textView = view as TextView
    }
}