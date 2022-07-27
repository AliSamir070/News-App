package com.alisamir.newsapp.data

import com.alisamir.newsapp.pojo.NewsResponse
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.*

interface NewsAPI {
    @GET("top-headlines")
    suspend fun topBreakingNews(@Query("apiKey") apiKey:String,
                        @Query("category") category: String,
                        @Query("country") country: String
    ): NewsResponse

    @GET("everything")
    suspend fun getCategoryNews(@Query("apiKey") apiKey:String,
                        @Query("q") q:String,
                        @Query("from") from:Date,
                        @Query("to") to:Date,
                        @Query("sortBy") sortBy:String
    ):NewsResponse
}