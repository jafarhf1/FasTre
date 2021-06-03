package com.example.fastre.core.data.source.remote.response.news

import com.google.gson.annotations.SerializedName

data class ListNewsResponse(
    @field:SerializedName("posts")
    val news: List<NewsResponse>
)