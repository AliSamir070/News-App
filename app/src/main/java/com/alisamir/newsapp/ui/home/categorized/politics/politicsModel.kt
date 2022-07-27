package com.alisamir.newsapp.ui.home.categorized.politics

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alisamir.newsapp.data.NewsWebServiceInstance
import com.alisamir.newsapp.pojo.Article
import com.alisamir.newsapp.security.API_KEY
import kotlinx.coroutines.launch

class politicsModel:ViewModel() {
    private val _politicsArticles = MutableLiveData<List<Article>>()
    val politicsArticles:LiveData<List<Article>>
    get() = _politicsArticles

    init {
        getPoliticsArticles()
    }
    fun getPoliticsArticles(){
        viewModelScope.launch {
            val politicsResponse = NewsWebServiceInstance.newsWebService.topBreakingNews(API_KEY , "politics","eg")
            _politicsArticles.value = politicsResponse.articles
        }
    }
}