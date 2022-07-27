package com.alisamir.newsapp.data

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.create

const val BASE_URL = "https://newsapi.org/v2/"
val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
val newsApiBuilder = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

object NewsWebServiceInstance{
    val newsWebService:NewsAPI by lazy {
        newsApiBuilder.create()
    }
}
