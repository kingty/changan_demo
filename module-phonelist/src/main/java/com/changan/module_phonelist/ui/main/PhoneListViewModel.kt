package com.changan.module_phonelist.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.changan.module_phonelist.data.Item
import com.changan.module_phonelist.logic.ItemProvider

class PhoneListViewModel : ViewModel() {

    private val itemProvider = ItemProvider()
    val items: MutableLiveData<List<Item>> = MutableLiveData()


    fun refreshItems() {
        itemProvider.getItems().subscribe {
            items.value = it
        }

    }

}