package com.changan.module_favorites

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.changan.module_base.PATH_FAVORITE_ACT
import com.changan.module_favorites.ui.main.FavoritesFragment

@Route(path = PATH_FAVORITE_ACT)
class FavoritesActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorites)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, FavoritesFragment.newInstance())
                .commitNow()
        }
    }
}