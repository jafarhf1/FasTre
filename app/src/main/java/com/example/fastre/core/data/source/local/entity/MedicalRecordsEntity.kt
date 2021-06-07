package com.example.fastre.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medical_records")
data class MedicalRecordsEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "date")
        var medicalDate: String,

        @ColumnInfo(name = "polyId")
        var medicalPolyId: String,

        @ColumnInfo(name = "number")
        var medicalUserNumber: Int?,

        @ColumnInfo(name = "userId")
        var medicalQueueId: String?
)