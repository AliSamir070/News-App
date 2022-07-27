package com.alisamir.newsapp.ui.home.categorized.business

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alisamir.newsapp.data.NewsWebServiceInstance
import com.alisamir.newsapp.pojo.Article
import com.alisamir.newsapp.security.API_KEY
import kotlinx.coroutines.launch

class businessModel: ViewModel() {
    private val _businessArticles = MutableLiveData<List<Article>>()
    val businessArticles:LiveData<List<Article>>
    get() = _businessArticles

    init {
        getBusinessArticles()
    }
    fun getBusinessArticles(){
      viewModelScope.launch {

         val businessResponse = NewsWebServiceInstance.newsWebService.topBreakingNews(API_KEY , "business" , "eg")
          _businessArticles.value = businessResponse.articles
      }
    }
}