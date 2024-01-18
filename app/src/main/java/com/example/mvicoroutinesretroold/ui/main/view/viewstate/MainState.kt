package com.example.mvicoroutinesretroold.ui.main.view.viewstate

import com.example.mvicoroutinesretroold.ui.data.model.User

sealed class MainState {

    object Idle : MainState()
    object Loading : MainState()
    data class Users(val usersList: List<User>) : MainState()
    data class Error(val error: String?) : MainState()

}