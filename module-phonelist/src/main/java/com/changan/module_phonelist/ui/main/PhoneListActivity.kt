package com.changan.module_phonelist.ui.main

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.changan.module_base.PATH_LIST_ACT
import com.changan.module_phonelist.R

@Route(path = PATH_LIST_ACT)
class PhoneListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_phone_list)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, PhoneListFragment.newInstance())
                .commitNow()
        }
    }
}