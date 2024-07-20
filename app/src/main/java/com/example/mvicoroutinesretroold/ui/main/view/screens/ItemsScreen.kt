package com.example.mvicoroutinesretroold.ui.main.view.screens

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.os.bundleOf
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mvicoroutinesretroold.ui.data.api.ApiHelperImpl
import com.example.mvicoroutinesretroold.ui.data.api.RetrofitBuilder
import com.example.mvicoroutinesretroold.ui.data.model.User
import com.example.mvicoroutinesretroold.ui.main.view.viewmodel.MainViewModel
import com.example.mvicoroutinesretroold.ui.util.ViewModelFactory

@Composable
fun ItemsScreen(
    lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current,
    onItemClick: (User) -> Unit
) {

    val mainViewModel: MainViewModel =
        viewModel(
            factory = ViewModelFactory(
                ApiHelperImpl(
                    RetrofitBuilder.apiService
                )
            )
        )

    val state by mainViewModel.state.collectAsState()

    PerformOnLifeCycle(lifecycleOwner = lifecycleOwner,
        onStart = {
            mainViewModel.fetchUser()
            Log.d("zama", "lifecycle...onStart")
        },
        onResume = { Log.d("zama", "lifecycle...onResume") }
    )

    state.onSuccess {
        Log.d("zama", "...onSuccess")
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.Start,
        ) {
            Log.d("zama", "LazyColumn ...outside")
            items(it) { item ->
                Log.d("zama", "LazyColumn .... inside")
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clickable {
                            onItemClick(item)
//                            makeACall(, user)
//                            Log.d("zama", item.name)
                        }
                        .height(60.dp)
                        .padding(
                            horizontal = 40.dp,
                            vertical = 4.dp,
                        )
                        .clip(RoundedCornerShape(10.dp))
                        .background(Color(0xFFFFEB3B)),
                ) {
                    Text(
                        text = item.name,
                        fontSize = 40.sp,
                        color = Color.Red,
                        modifier = Modifier.padding(start = 10.dp).align(Alignment.Center).clickable {
//                            Log.d("zama", "zama ... text ......${item.name}")
                        }
                    )
                }
            }
        }
//        Log.d("zama", "${it.size}")
    }.onFailure {
        Log.d("zama", "onFailure $it")
    }


//    when (val screenState = state) {
//        is MainState.Failure -> Log.d("zama", "${screenState.error}")
//        is MainState.Idle -> Log.d("zama", "Idle")
//        is MainState.Loading -> Log.d("zama", "Loading")
//        is MainState.Success -> {
//
//            LazyColumn(Modifier.padding(start = 6.dp, end = 6.dp)) {
//                items(screenState.usersList) { item ->
//                    Log.d("zama", "LazyColumn")
//                    Text(text = item.name, modifier = Modifier.padding(10.dp))
//                }
//            }
//            Log.d("zama", "${screenState.usersList}")
//
//        }
//    }

}

fun makeACall(context: Context, user: User) {
    val intent = Intent(Intent.ACTION_CALL)
    //make sure you have permission to make a call
    startActivity(context, intent, bundleOf())
}