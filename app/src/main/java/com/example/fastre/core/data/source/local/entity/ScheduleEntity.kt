package com.example.fastre.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "schedule")
data class ScheduleEntity(
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "doctorId")
    var doctorId: Int,

    @ColumnInfo(name = "doctorName")
    var doctorName: String,

    @ColumnInfo(name = "doctorPoly")
    var doctorPoly: Int,

    @ColumnInfo(name = "doctorPhoto")
    var doctorPhoto: String,

    @ColumnInfo(name = "doctorSchedule1")
    var doctorSchedule1: String,

    @ColumnInfo(name = "doctorSchedule2")
    var doctorSchedule2: String,

    @ColumnInfo(name = "doctorSchedule3")
    var doctorSchedule3: String,
)
