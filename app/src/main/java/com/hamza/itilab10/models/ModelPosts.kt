package com.hamza.itilab10.models

class ModelPosts : ArrayList<ModelPosts.ModelPostsItem>(){
    data class ModelPostsItem(
        val body: String,
        val id: Int,
        val title: String,
        val userId: Int
    )
}