package com.changan.module_main.architec

import android.app.Activity
import android.content.Context
import android.content.ContextWrapper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tantan.build.ui.app.Act

interface IViewModel<out P : IPresenter<IViewModel<P>>> {

    /**
     * 绑定presenter，一般在此方法中持有presenter的对象
     */
    fun bindPresenter(presenter: @UnsafeVariance P)
    fun inflateView(inflater: LayoutInflater?, parent: ViewGroup?): View?

    /**
     * 释放资源
     */
    fun destroy()

    /**
     *
     */
    fun context(): Context?

    /**
     * @return activity get from context, if activity is act; or null if activity is not act
     */
    fun act(): Act? {
        val activity = getActivityFromContext(context())
        return if (activity is Act) {
            activity
        } else null
    }

    companion object {
        fun getActivityFromContext(context: Context?): Activity? {
            var context = context
            while (context is ContextWrapper) {
                if (context is Activity) {
                    return context
                }
                context = context.baseContext
            }
            return null
        }
    }
}