package com.alisamir.newsapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alisamir.newsapp.data.NewsWebServiceInstance
import com.alisamir.newsapp.pojo.Article
import com.alisamir.newsapp.security.API_KEY
import kotlinx.coroutines.launch

class HomeNewsModel:ViewModel() {
    private val _breakingNewsArticles = MutableLiveData<List<Article>>()
    val breakingNewsArticles:LiveData<List<Article>>
    get() = _breakingNewsArticles
    init {
        getBreakingNews()
    }
    fun getBreakingNews(){
        viewModelScope.launch {
            val articles = NewsWebServiceInstance.newsWebService.topBreakingNews(API_KEY , "general" , "eg").articles
            _breakingNewsArticles.value = articles
        }
    }
}