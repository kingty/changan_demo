package com.changan.module_phonelist.ui.main

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.changan.module_phonelist.R
import kotlinx.android.synthetic.main.fragment_phone_list.*

class PhoneListFragment : Fragment() {

    companion object {
        fun newInstance() = PhoneListFragment()
    }

    private lateinit var viewModel: PhoneListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_phone_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(PhoneListViewModel::class.java)
        recycler.layoutManager = LinearLayoutManager(context)
        val adapter = ItemAdapter(requireContext())
        recycler.adapter = adapter
        viewModel.items.observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
        viewModel.refreshItems()


    }

}