package com.alisamir.newsapp.ui.home.categorized.entertainment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alisamir.newsapp.data.NewsWebServiceInstance
import com.alisamir.newsapp.pojo.Article
import com.alisamir.newsapp.security.API_KEY
import kotlinx.coroutines.launch

class entertainmentModel:ViewModel() {
    private val _entertainmentArticles = MutableLiveData<List<Article>>()
    val entertainmentArticles:LiveData<List<Article>>
    get() = _entertainmentArticles

    init {
        getEntertainmentArticles()
    }
    fun getEntertainmentArticles(){
        viewModelScope.launch {
            val entertainmentResponse = NewsWebServiceInstance.newsWebService.topBreakingNews(API_KEY,"entertainment","eg")
            _entertainmentArticles.value = entertainmentResponse.articles
        }
    }
}