package com.hamza.itilab10.models

class ModelComments : ArrayList<ModelComments.ModelCommentsItem>(){
    data class ModelCommentsItem(
        val body: String,
        val email: String,
        val id: Int,
        val name: String,
        val postId: Int
    )
}