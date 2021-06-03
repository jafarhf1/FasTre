package com.example.fastre.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "hospital")
data class HospitalEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "hospitalId")
    var hospitalId: Int,

    @ColumnInfo(name = "hospitalName")
    var hospitalName: String,

    @ColumnInfo(name = "hospitalPhone")
    var hospitalPhone: String,

    @ColumnInfo(name = "hospitalWhatsapp")
    var hospitalWhatsapp: String,

    @ColumnInfo(name = "hospitalLongitude")
    var hospitalLongitude: Double,

    @ColumnInfo(name = "hospitalLatitude")
    var hospitalLatitude: Double,

    @ColumnInfo(name = "hospitalPhoto1")
    var hospitalPhoto1: String,

    @ColumnInfo(name = "hospitalPhoto2")
    var hospitalPhoto2: String,

    @ColumnInfo(name = "hospitalPhoto3")
    var hospitalPhoto3: String
)