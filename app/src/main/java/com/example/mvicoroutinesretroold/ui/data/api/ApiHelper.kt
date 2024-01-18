package com.example.mvicoroutinesretroold.ui.data.api

import com.example.mvicoroutinesretroold.ui.data.model.User

interface ApiHelper {
    suspend fun getUsers() : List<User>
}