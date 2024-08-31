package com.pr7.jc_retrofit_mvi_jsonplaceholder

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pr7.jc_retrofit_mvi_jsonplaceholder.event.AppIntent
import com.pr7.jc_retrofit_mvi_jsonplaceholder.ui.MainViewModel
import com.pr7.jc_retrofit_mvi_jsonplaceholder.ui.theme.JC_RETROFIT_MVI_JsonPlaceHolderTheme

class MainActivity : ComponentActivity() {

        val mainViewModel:MainViewModel by viewModels<MainViewModel>()
        override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            JC_RETROFIT_MVI_JsonPlaceHolderTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        MainScreen(
                            mainViewModel = mainViewModel
                        )
                    }

                }
            }
        }
    }
}

@Composable
fun MainScreen(mainViewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Counter Section
        Text(text = "Count: ${mainViewModel.counterState.count}")
        Button(onClick = {
            mainViewModel.handleIntent(AppIntent.IncrementCounter)
        }) {
            Text(text = "Increment Counter")
        }

        // Divider
        Text(text = "-----------------", modifier = Modifier.padding(vertical = 16.dp))

        Log.d("PR77777", "MainScreen: RECOMPOSITION")

        when {
            mainViewModel.postState.isLoading -> Text(text = "Loading...")
            mainViewModel.postState.error != null -> Text(text = "Error: ${mainViewModel.postState.error}")
            mainViewModel.postState.post != null -> Text(text = "Title: ${mainViewModel.postState.post!!.title}\n\nBody: ${mainViewModel.postState.post!!.body}")
        }

        // GET request
        Button(onClick = {
           mainViewModel.handleIntent(AppIntent.FetchPost)
        }) {
            Text(text = "Fetch Post")
        }


        // POST request
        Button(onClick = {
            mainViewModel.handleIntent(AppIntent.CreatePost)
        }) {
            Text(text = "Create Post")
        }

    }
}

