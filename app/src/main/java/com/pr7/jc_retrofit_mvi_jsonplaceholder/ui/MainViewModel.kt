package com.pr7.jc_retrofit_mvi_jsonplaceholder.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pr7.jc_retrofit_mvi_jsonplaceholder.data.remote.RetrofitInstance.apiService
import com.pr7.jc_retrofit_mvi_jsonplaceholder.event.AppIntent
import com.pr7.jc_retrofit_mvi_jsonplaceholder.event.CounterState
import com.pr7.jc_retrofit_mvi_jsonplaceholder.event.PostState
import com.pr7.jc_retrofit_mvi_jsonplaceholder.model.NewPost
import kotlinx.coroutines.launch


class MainViewModel :ViewModel(){

     var counterState:CounterState by mutableStateOf(CounterState(count = 0))

    var postState:PostState by mutableStateOf(PostState())





    fun handleIntent(intent: AppIntent){
        when(intent){
            is AppIntent.IncrementCounter->{
                counterState = counterState.copy(count = counterState.count + 1)
            }
            is AppIntent.FetchPost->{
                getPost()
            }
            is AppIntent.CreatePost->{
                createPost()
            }
            else -> {}
        }
    }


    private fun getPost()=viewModelScope.launch {
        try {
            postState=PostState(isLoading = true)
            val post = apiService.getPost()
            postState=PostState(post = post,isLoading = false)
        } catch (e: Exception) {
            postState=PostState(error = e.message.toString(), isLoading = false)
        }
    }

    private fun createPost() {

        viewModelScope.launch {
            try {
                postState=PostState(isLoading = true)
                val newPost = NewPost(userId = 1, title = "New Title", body = "New Body")
                val post = apiService.createPost(newPost)
                postState=PostState(post = post,isLoading = false)

            } catch (e: Exception) {
                postState=PostState(error = e.message.toString(), isLoading = false)
            }
        }
    }

}