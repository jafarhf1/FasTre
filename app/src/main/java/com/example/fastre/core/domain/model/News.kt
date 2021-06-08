package com.example.fastre.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class News(
    val id: String,
    val title: String,
    val detail: String,
    val date: String,
    val photo: String,
    val isBookmarked: Boolean
): Parcelable