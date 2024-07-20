package com.example.mvicoroutinesretroold.ui.main.view.viewstate

import androidx.compose.runtime.Composable
import com.example.mvicoroutinesretroold.ui.data.model.User

sealed class MainState {

    data object Idle : MainState()
    data object Loading : MainState()
    data class Success(val usersList: List<User>) : MainState()
    data class Failure(val error: String?) : MainState()


    @Composable
    fun onSuccess(action: @Composable (usersList: List<User>) -> Unit): MainState {
        if (this is Success) {
            action(usersList)
        }
        return this
    }

    @Composable
    fun onFailure(action: @Composable (errorReason: String?) -> Unit): MainState {
        if (this is Failure) {
            action(error)
        }
        return this
    }

}