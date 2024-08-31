package com.pr7.jc_retrofit_mvi_jsonplaceholder.model





data class Post(val userId: Int, val id: Int, val title: String, val body: String)
data class NewPost(val userId: Int, val title: String, val body: String)