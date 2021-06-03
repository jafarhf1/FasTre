package com.example.fastre.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news")
data class NewsEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "id")
    var id: String,

    @ColumnInfo(name = "title")
    var title: String,

    @ColumnInfo(name = "detail")
    var detail: String,

    @ColumnInfo(name = "date")
    var date: String,

    @ColumnInfo(name = "photo")
    var photo: String,

    @ColumnInfo(name = "isBookmarked")
    var isBookmarked: Boolean = false
)