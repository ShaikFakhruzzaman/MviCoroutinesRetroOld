package com.example.mvicoroutinesretroold.ui.main.view.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.DisposableEffect
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleEventObserver
import androidx.lifecycle.LifecycleOwner

@Composable
fun PerformOnLifeCycle(
    lifecycleOwner: LifecycleOwner,
    onStart: ()->Unit = { },
    onResume: ()->Unit = { }
    ){

    DisposableEffect(lifecycleOwner) {
        val lifecycleEventObserver = LifecycleEventObserver{ _, event ->
            when(event){
                Lifecycle.Event.ON_START -> onStart()
                Lifecycle.Event.ON_RESUME -> onResume()
                else ->{}
            }
        }

        lifecycleOwner.lifecycle.addObserver(lifecycleEventObserver)
        return@DisposableEffect onDispose {
            lifecycleOwner.lifecycle.removeObserver(lifecycleEventObserver)
        }
    }
}