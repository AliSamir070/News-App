package com.alisamir.newsapp.ui.home.categorized.science

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alisamir.newsapp.data.NewsWebServiceInstance
import com.alisamir.newsapp.pojo.Article
import com.alisamir.newsapp.security.API_KEY
import kotlinx.coroutines.launch

class ScienceModel:ViewModel() {
    private val _scienceArticles = MutableLiveData<List<Article>>()
    val scienceArticles:LiveData<List<Article>>
    get() = _scienceArticles

    init {
        getScienceArticles()
    }

    fun getScienceArticles(){
        viewModelScope.launch {
            val scienceResponse = NewsWebServiceInstance.newsWebService.topBreakingNews(API_KEY , "science","eg")
            _scienceArticles.value = scienceResponse.articles
        }
    }
}