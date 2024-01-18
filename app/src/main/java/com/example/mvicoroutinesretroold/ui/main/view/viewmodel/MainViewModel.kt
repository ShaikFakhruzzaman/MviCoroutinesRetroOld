package com.example.mvicoroutinesretroold.ui.main.view.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mvicoroutinesretroold.ui.data.repository.MainRepository
import com.example.mvicoroutinesretroold.ui.main.view.mainintent.MainIntent
import com.example.mvicoroutinesretroold.ui.main.view.viewstate.MainState
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.consumeAsFlow
import kotlinx.coroutines.launch

class MainViewModel(private val mainRepository: MainRepository) : ViewModel() {

    val userIntent = Channel<MainIntent>()
    private val _state = MutableStateFlow<MainState>(MainState.Idle)
    val state: StateFlow<MainState>
        get() = _state

    init {
        handleIntent()
    }

    public fun handleIntent() {
        viewModelScope.launch {
            userIntent.consumeAsFlow().collect{
                when (it) {
                    MainIntent.FetchUser -> fetchUser()
                }
            }
        }
    }

    public fun fetchUser() {
        viewModelScope.launch {
            _state.value = MainState.Loading
            _state.value = try {
                MainState.Users(mainRepository.getUsers())
            } catch (e: Exception) {
                MainState.Error(e.localizedMessage)
            }
        }

    }


}