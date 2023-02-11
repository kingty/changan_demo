package com.changan.module_main.ui

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import com.changan.module_main.R
import com.changan.module_main.architec.IViewModel

class MainViewModel : IViewModel<MainPresenter> {
    private var presenter: MainPresenter? = null
    override fun bindPresenter(presenter: MainPresenter) {
        this.presenter = presenter
    }

    override fun destroy() {
    }


    override fun context(): Context? {
        return null
    }


    fun setTimeText(result: Long) {
        time?.text = result.toString()
    }

    var time: TextView? = null
    override fun inflateView(inflater: LayoutInflater?, parent: ViewGroup?): View? {
        val view: View =
            inflater!!.inflate(R.layout.activity_main, parent, false)

        val to_list = view.findViewById<Button>(R.id.to_list)
        val to_favorites = view.findViewById<Button>(R.id.to_favorites)
        val service_get = view.findViewById<Button>(R.id.service_get)
        time = view.findViewById<Button>(R.id.time)
        to_list.setOnClickListener {
            presenter?.toListAct()
        }
        to_favorites.setOnClickListener {
            presenter?.toFavoriteAct()
        }

        service_get.setOnClickListener {
            presenter?.getListServiceTime()
        }
        return view
    }

}