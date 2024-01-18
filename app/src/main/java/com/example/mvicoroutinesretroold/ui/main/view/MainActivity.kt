package com.example.mvicoroutinesretroold.ui.main.view

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.mvicoroutinesretroold.R
import com.example.mvicoroutinesretroold.ui.data.api.ApiHelperImpl
import com.example.mvicoroutinesretroold.ui.data.api.RetrofitBuilder
import com.example.mvicoroutinesretroold.ui.main.view.mainintent.MainIntent
import com.example.mvicoroutinesretroold.ui.main.view.viewmodel.MainViewModel
import com.example.mvicoroutinesretroold.ui.main.view.viewstate.MainState
import com.example.mvicoroutinesretroold.ui.util.ViewModelFactory
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {


    private lateinit var mainViewModel: MainViewModel
    lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById(R.id.buttonFetchUser)

        setupViewModel()
        buttonClick()
    }

    private fun buttonClick() {
        button.setOnClickListener {
            lifecycleScope.launch {
                mainViewModel.state.collect {
                    when (it) {
                        is MainState.Error -> {
                            Toast.makeText(this@MainActivity, "Error", Toast.LENGTH_LONG).show()
                        }

                        is MainState.Idle -> {
                            Toast.makeText(this@MainActivity, "Idle", Toast.LENGTH_LONG).show()
                        }

                        is MainState.Loading -> {
                            Toast.makeText(this@MainActivity, "Idle", Toast.LENGTH_LONG).show()
                        }

                        is MainState.Users -> {
                            Log.d("zama", "${it.usersList}")
                            Toast.makeText(this@MainActivity,"${it.usersList}", Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        }

    }


    private fun setupViewModel() {
        mainViewModel = ViewModelProvider(
            this, ViewModelFactory(
                ApiHelperImpl(
                    RetrofitBuilder.apiService
                )
            )
        )[MainViewModel::class.java]

        lifecycleScope.launch {
            mainViewModel.userIntent.send(MainIntent.FetchUser)
        }
    }
}