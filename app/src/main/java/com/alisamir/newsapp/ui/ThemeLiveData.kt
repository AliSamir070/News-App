package com.alisamir.newsapp.ui

import androidx.lifecycle.LiveData

class ThemeLiveData: LiveData<Boolean>() {
    override fun onActive() {
        super.onActive()
    }

    override fun onInactive() {
        super.onInactive()
    }
    fun setTheme(isDark:Boolean){
        postValue(isDark)
    }
}