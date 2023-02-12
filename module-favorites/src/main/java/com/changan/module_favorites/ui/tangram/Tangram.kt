package com.changan.module_favorites.ui.tangram

import android.content.Context
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.NonNull
import androidx.annotation.Nullable
import com.changan.module_favorites.ui.tangram.util.Utils
import com.changan.module_favorites.ui.tangram.view.*
import com.tmall.wireless.tangram.TangramBuilder
import com.tmall.wireless.tangram.TangramEngine
import com.tmall.wireless.tangram.support.async.CardLoadSupport
import com.tmall.wireless.tangram.util.IInnerImageSetter
import com.tmall.wireless.vaf.framework.VafContext
import com.tmall.wireless.vaf.virtualview.event.EventManager
import org.json.JSONArray
import org.json.JSONException

object Tangram {
    private val TAG = "Tangram"
    private fun setUpBuilder(context: Context): TangramBuilder.InnerBuilder {
        // 初始化 Tangram 环境
        TangramBuilder.init(context, object : IInnerImageSetter {
            override fun <IMAGE : ImageView?> doLoadImageUrl(
                @NonNull view: IMAGE,
                @Nullable url: String?
            ) {
                //  图片加载器
            }
        }, ImageView::class.java)
        // 初始化 TangramBuilder
        val builder = TangramBuilder.newInnerBuilder(context)
        // 注册自定义的卡片和组件
        builder.registerCell("InterfaceCell", CustomInterfaceView::class.java)
        builder.registerCell("AnnotationCell", CustomAnnotationView::class.java)
        builder.registerCell("CustomCell", CustomCell::class.java, CustomCellView::class.java)
        builder.registerCell("NoBackground", NoBackgroundView::class.java)
        // 注册 VirtualView 版本的 Tangram 组件
        builder.registerVirtualView<View>("VVTest")

        return builder
    }

    fun setUpEngine(context: Context): TangramEngine {
        var engine: TangramEngine? = null
        // 生成TangramEngine实例
        engine = setUpBuilder(context).build()
        // 加载VirtualView模板数据
//        mEngine?.setVirtualViewTemplate(VVTEST.BIN);
        engine?.setVirtualViewTemplate(Utils.getAssetsFile(context, "VVTest.out"))
        // 绑定业务 support 类到 engine
        // 处理点击
        engine?.addSimpleClickSupport(CustomClickSupport())
        // 处理曝光
        engine?.addExposureSupport(CustomExposureSupport())
        // 异步加载数据
        val cardLoadSupport = CardLoadSupport({ card, callback ->
            Log.d(
                TAG,
                "loadData: cardType=" + card.stringType
            )
        }
        ) { page, card, callback ->
            Log.d(
                TAG,
                "loadData: page=" + page + ", cardType=" + card.stringType
            )
        }
        CardLoadSupport.setInitialPage(1)
        engine?.addCardLoadSupport(cardLoadSupport)
        val vafContext: VafContext = engine!!.getService<VafContext>(VafContext::class.java)
        // 注册VirtualView事件处理器
        vafContext.eventManager.register(
            EventManager.TYPE_Click
        ) { data ->
            Toast.makeText(
                context,
                data.mVB.action,
                Toast.LENGTH_SHORT
            ).show()
            true
        }
        vafContext.eventManager.register(
            EventManager.TYPE_Exposure
        ) { data ->
            Log.d(
                TAG,
                "Exposure process: " + data.mVB.viewCache.componentData
            )
            true
        }
        // 设置悬浮类型布局的偏移（可选）
        engine.layoutManager?.setFixOffset(0, 40, 0, 0)
        // 设置卡片预加载的偏移量（可选）
        engine.setPreLoadNumber(3)

        return engine
    }
}