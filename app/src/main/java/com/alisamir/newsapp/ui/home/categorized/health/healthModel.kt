package com.alisamir.newsapp.ui.home.categorized.health

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alisamir.newsapp.data.NewsWebServiceInstance
import com.alisamir.newsapp.pojo.Article
import com.alisamir.newsapp.security.API_KEY
import kotlinx.coroutines.launch

class healthModel :ViewModel(){
    private val _healthArticles = MutableLiveData<List<Article>>()
    val healthArticles:LiveData<List<Article>>
    get() = _healthArticles

    init {
        getHealthArticles()
    }
    fun getHealthArticles(){
        viewModelScope.launch {
            val healthResponse = NewsWebServiceInstance.newsWebService.topBreakingNews(API_KEY , "health","eg")
            _healthArticles.value = healthResponse.articles
        }
    }
}