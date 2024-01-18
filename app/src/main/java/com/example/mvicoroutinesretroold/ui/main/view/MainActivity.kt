package com.example.mvicoroutinesretroold.ui.main.view

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mvicoroutinesretroold.R
import com.example.mvicoroutinesretroold.ui.data.api.ApiHelperImpl
import com.example.mvicoroutinesretroold.ui.data.api.RetrofitBuilder
import com.example.mvicoroutinesretroold.ui.data.model.User
import com.example.mvicoroutinesretroold.ui.main.view.adapter.RecyclerAdapter
import com.example.mvicoroutinesretroold.ui.main.view.mainintent.MainIntent
import com.example.mvicoroutinesretroold.ui.main.view.viewmodel.MainViewModel
import com.example.mvicoroutinesretroold.ui.main.view.viewstate.MainState
import com.example.mvicoroutinesretroold.ui.util.ViewModelFactory
import kotlinx.coroutines.launch


class MainActivity : AppCompatActivity() {

    private lateinit var mainViewModel: MainViewModel
    private lateinit var button: Button
    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private val adapter = RecyclerAdapter(arrayListOf())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        progressBar = findViewById(R.id.progressBar)
        button = findViewById(R.id.buttonFetchUser)
        setupRecyclerView()
        setupViewModel()
        collectApiDataFromFlow()

        button.setOnClickListener {
            collectApiDataFromFlow()
        }
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.recyclerView)
//        button.visibility = View.GONE
        recyclerView.visibility = View.VISIBLE
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.run {
            addItemDecoration(
                DividerItemDecoration(
                    recyclerView.context,
                    (recyclerView.layoutManager as LinearLayoutManager).orientation
                )
            )
        }
        recyclerView.adapter = adapter
    }

    private fun collectApiDataFromFlow() {
            lifecycleScope.launch {
                mainViewModel.state.collect {
                    when (it) {
                        is MainState.Error -> {
                            Log.d("zama", "${it.error}")
                            Toast.makeText(this@MainActivity, "${it.error}", Toast.LENGTH_LONG).show()
                        }

                        is MainState.Idle -> {
                            Toast.makeText(this@MainActivity, "Idle", Toast.LENGTH_LONG).show()
                        }

                        is MainState.Loading -> {
                            progressBar.visibility = View.VISIBLE
                            Toast.makeText(this@MainActivity, "Loading", Toast.LENGTH_LONG).show()
                        }

                        is MainState.Users -> {
                            progressBar.visibility = View.GONE
                            renderList(it.usersList)
                            Toast.makeText(this@MainActivity, "${it.usersList}", Toast.LENGTH_LONG)
                                .show()
                        }
                    }
                }
            }
    }

    private fun renderList(usersList: List<User>) {
        usersList.let {
            adapter.addData(it)
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