package com.changan.module_favorites.ui.tangram

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.changan.module_favorites.R
import com.changan.module_favorites.ui.tangram.util.Utils
import com.tmall.wireless.tangram.TangramEngine
import kotlinx.android.synthetic.main.activity_tangram.*
import org.json.JSONArray
import org.json.JSONException

class TangramActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_tangram)
        initTangram()
    }

    var engine: TangramEngine? = null
    private fun initTangram() {

        engine = Tangram.setUpEngine(this)
        // 绑定 recyclerView
        engine?.bindView(recyclerView)
        // 监听 recyclerView 的滚动事件
        val scrollListener = object : RecyclerView.OnScrollListener() {
            override fun onScrolled(r: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(r, dx, dy)
                // 在 scroll 事件中触发 engine 的 onScroll，内部会触发需要异步加载的卡片去提前加载数据
                engine?.onScrolled()
            }
        }
        recyclerView.addOnScrollListener(scrollListener)

        // 加载数据并传递给 engine
        val bytes = Utils.getAssetsFile(this, intent.getStringExtra("filename"))
        if (bytes != null) {
            val json = String(bytes)
            try {
                val data = JSONArray(json)
                engine?.setData(data)
            } catch (e: JSONException) {
                e.printStackTrace()
            }
        }

    }

    override fun onDestroy() {
        // 退出的时候销毁 engine
        engine?.destroy()
        super.onDestroy()
    }
}