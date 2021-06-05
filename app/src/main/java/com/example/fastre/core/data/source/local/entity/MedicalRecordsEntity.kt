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
        var recordDate: Int,

        //@ColumnInfo(name = "polyId")
        //var recordPolyId: String
)