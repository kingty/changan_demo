package com.changan.module_phonelist.network

import com.changan.module_phonelist.data.Item
import io.reactivex.Observable
import retrofit2.http.GET

interface ItemApi {
    @GET("build.json")
    fun listItem(): Observable<List<Item>>
}