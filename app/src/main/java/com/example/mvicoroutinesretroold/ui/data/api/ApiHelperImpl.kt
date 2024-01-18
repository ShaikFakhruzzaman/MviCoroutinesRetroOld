package com.example.mvicoroutinesretroold.ui.data.api

import com.example.mvicoroutinesretroold.ui.data.model.User

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {
    override suspend fun getUsers(): List<User> {
        return apiService.getUsers()
    }
}