package com.changan.module_main.architec

interface IPresenter<out V : IViewModel<IPresenter<V>>> {
    /**
     * 绑定viewModel，一般在此方法中持有viewModel的对象
     */
    fun bindViewModel(viewModel: @UnsafeVariance V)

    /**
     * 释放资源
     */
    fun destroy()
}