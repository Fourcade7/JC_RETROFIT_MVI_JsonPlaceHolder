package com.pr7.jc_retrofit_mvi_jsonplaceholder.event

import com.pr7.jc_retrofit_mvi_jsonplaceholder.model.Post


// Define states
data class CounterState(var count: Int = 0)
data class PostState(
    val post: Post? = null,
    val isLoading: Boolean = false,
    val error: String? = null
)

// Define intents
sealed class AppIntent {
    object IncrementCounter : AppIntent()
    object FetchPost : AppIntent()
    object CreatePost : AppIntent()
}