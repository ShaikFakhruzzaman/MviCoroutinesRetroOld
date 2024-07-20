package com.example.mvicoroutinesretroold.ui.main.view

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.mvicoroutinesretroold.ui.main.view.screens.ItemsScreen
import com.example.mvicoroutinesretroold.ui.theme.MviCoroutinesRetroOldTheme

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MviCoroutinesRetroOldTheme{
                ItemsScreen(onItemClick = { user ->
                    Log.d("zama", user.name)
                    Toast.makeText(this, "${user.name}", Toast.LENGTH_SHORT).show()
                })
            }
        }
    }
}