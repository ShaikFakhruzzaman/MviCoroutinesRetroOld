package com.example.mvicoroutinesretroold.ui.util

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.mvicoroutinesretroold.ui.data.api.ApiHelper
import com.example.mvicoroutinesretroold.ui.data.repository.MainRepository
import com.example.mvicoroutinesretroold.ui.main.view.viewmodel.MainViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(MainViewModel::class.java)){
            return MainViewModel(MainRepository(apiHelper)) as T
        }
        throw IllegalArgumentException("Unknown class name")
    }


}