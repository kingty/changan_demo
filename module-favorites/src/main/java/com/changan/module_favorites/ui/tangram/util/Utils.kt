package com.changan.module_favorites.ui.tangram.util

import android.app.Activity
import android.content.Context
import org.json.JSONException
import org.json.JSONObject
import java.io.*
import java.lang.Exception
import java.lang.StringBuilder

object Utils {
    /**
     * 获取assets目录下文件的字节数组
     *
     * @param context  上下文
     * @param fileName assets目录下的文件
     * @return assets目录下文件的字节数组
     */
    fun getAssetsFile(context: Context, fileName: String?): ByteArray? {
        val inputStream: InputStream
        val assetManager = context.assets
        try {
            inputStream = assetManager.open(fileName!!)
            var bis: BufferedInputStream? = null
            val length: Int
            try {
                bis = BufferedInputStream(inputStream)
                length = bis.available()
                val data = ByteArray(length)
                bis.read(data)
                return data
            } catch (e: IOException) {
                e.printStackTrace()
            } finally {
                if (bis != null) {
                    try {
                        bis.close()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            return null
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 获取assets目录下json文件的数据
     *
     * @param context 上下文
     * @param name    assets目录下的json文件名
     * @return json数据对象
     */
    fun getJSONDataFromAsset(context: Context, name: String?): JSONObject? {
        try {
            val inputStream = context.assets.open(name!!)
            val inputStreamReader = BufferedReader(InputStreamReader(inputStream))
            val sb = StringBuilder()
            var str: String?
            while (inputStreamReader.readLine().also { str = it } != null) {
                sb.append(str)
            }
            inputStreamReader.close()
            return JSONObject(sb.toString())
        } catch (e: IOException) {
            e.printStackTrace()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * 根据手机的分辨率从 dp 的单位转成为 px(像素)
     *
     * @param context  上下文
     * @param dipValue dip或dp大小
     * @return 像素值
     */
    fun dip2px(context: Context?, dipValue: Float): Int {
        if (context != null) {
            try {
                return (dipValue * getScreenDensity(context) + 0.5f).toInt()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return 0
    }

    /**
     * 获取屏幕密度
     *
     * @param context 上下文
     * @return 屏幕密度
     */
    fun getScreenDensity(context: Context): Float {
        return context.resources.displayMetrics.density
    }

    /**
     * 判断用于Glide的Context是否有效
     *
     * @param context 用于Glide的Context
     * @return 用于Glide的Context是否有效
     */
    fun isValidContextForGlide(context: Context?): Boolean {
        if (context == null) {
            return false
        }
        if (context is Activity) {
            val activity = context
            return !activity.isDestroyed && !activity.isFinishing
        }
        return true
    }
}