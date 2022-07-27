package com.alisamir.newsapp.ui.home.categorized.sports

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alisamir.newsapp.data.NewsWebServiceInstance
import com.alisamir.newsapp.pojo.Article
import com.alisamir.newsapp.security.API_KEY
import kotlinx.coroutines.launch

class sportsModel: ViewModel() {
    private val _sportsArticles = MutableLiveData<List<Article>>()
    val sportsArticles:LiveData<List<Article>>
    get() = _sportsArticles

    init {
        getSportsArticles()
    }
    fun getSportsArticles(){
        viewModelScope.launch {
            val sportsResponse = NewsWebServiceInstance.newsWebService.topBreakingNews(API_KEY , "sports","eg")
            _sportsArticles.value = sportsResponse.articles
        }
    }
}