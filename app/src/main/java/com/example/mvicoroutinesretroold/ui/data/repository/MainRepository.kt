package com.example.mvicoroutinesretroold.ui.data.repository

import com.example.mvicoroutinesretroold.ui.data.api.ApiHelper

class MainRepository(private val apiHelper: ApiHelper) {
    suspend fun getUsers() = apiHelper.getUsers()
}