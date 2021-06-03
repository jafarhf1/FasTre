package com.example.fastre.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "poly")
data class PolyEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "polyId")
    var polyId: Int,

    @ColumnInfo(name = "polyName")
    var polyName: String,
)
