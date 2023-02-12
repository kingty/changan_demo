package com.changan.module_favorites.ui.main

import android.content.Intent
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.changan.module_base.service.ServiceProvider
import com.changan.module_favorites.R
import com.changan.module_favorites.ui.tangram.TangramActivity
import kotlinx.android.synthetic.main.fragment_favorites.*

class FavoritesFragment : Fragment() {

    companion object {
        fun newInstance() = FavoritesFragment()
    }

    private lateinit var viewModel: FavoritesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_favorites, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(FavoritesViewModel::class.java)
        // TODO: Use the ViewModel


        demo.setOnClickListener {
            val intent = Intent(context, TangramActivity::class.java)
            intent.putExtra("filename", "data.json")
            startActivity(intent)
        }
        demo2.setOnClickListener {
            val intent = Intent(context, TangramActivity::class.java)
            intent.putExtra("filename", "data_1.json")
            startActivity(intent)
        }

    }

}