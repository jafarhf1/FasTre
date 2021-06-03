package com.example.fastre.core.data.source.remote.response.news

import com.google.gson.annotations.SerializedName

data class NewsResponse(
    @field:SerializedName("id")
    val id: String,

    @field:SerializedName("postTitle")
    val title: String,

    @field:SerializedName("postContent")
    val detail: String,

    @field:SerializedName("date")
    val date: String,

    @field:SerializedName("imgURL")
    val photo: String
)