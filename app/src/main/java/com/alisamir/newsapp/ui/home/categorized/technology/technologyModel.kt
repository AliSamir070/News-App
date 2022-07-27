package com.alisamir.newsapp.ui.home.categorized.technology

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alisamir.newsapp.data.NewsWebServiceInstance
import com.alisamir.newsapp.pojo.Article
import com.alisamir.newsapp.security.API_KEY
import kotlinx.coroutines.launch

class technologyModel:ViewModel() {
    private val _technologyArticles = MutableLiveData<List<Article>>()
    val technologyArticles:LiveData<List<Article>>
    get() = _technologyArticles

    init {
        getTechnologyArticles()
    }

    fun getTechnologyArticles(){
        viewModelScope.launch {
            val technologyResponse = NewsWebServiceInstance.newsWebService.topBreakingNews(API_KEY , "technology","eg")
            _technologyArticles.value = technologyResponse.articles
        }
    }
}