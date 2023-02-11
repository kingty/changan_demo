package com.changan.module_phonelist.network

import com.changan.module_phonelist.data.Item
import io.reactivex.Observable
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class RemoteFetcher {

    companion object {
        lateinit var retrofit: Retrofit
        lateinit var itemApi: ItemApi
        private fun initRetrofit() {
            val builder = OkHttpClient.Builder()

                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(40, TimeUnit.SECONDS)


            builder.addInterceptor {
                val original = it.request()

                val request = original.newBuilder()
                    .header("common-header", "header")
                    .method(original.method(), original.body())
                    .build()

                it.proceed(request)
            }
            val http = builder.build()!!

            retrofit = Retrofit.Builder()
                .baseUrl("http://baidu.com")
                .client(http)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            itemApi = retrofit.create(ItemApi::class.java)!!
        }


    }

    init {
        initRetrofit()
    }

    fun refreshItemFromRemote(): Observable<List<Item>> {
        return itemApi.listItem()
    }


}