package com.example.fastre.core.data.source.local.entity

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "medical_records")
data class MedicalRecordsEntity(
        @PrimaryKey
        @NonNull
        @ColumnInfo(name = "medicalRecordId")
        var medicalRecordId: Int,

        @ColumnInfo(name = "medicalRecordPoli")
        var medicalRecordPoli: String,

        @ColumnInfo(name = "date")
        var date: Int,
)