package com.example.mvicoroutinesretroold.ui.data.api

import com.example.mvicoroutinesretroold.ui.data.model.User
import retrofit2.http.GET

interface ApiService {
    @GET("users")
    suspend fun getUsers() : List<User>
}