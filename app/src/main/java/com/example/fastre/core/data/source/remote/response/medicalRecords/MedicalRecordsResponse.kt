package com.example.fastre.core.data.source.remote.response.medicalRecords

import com.google.gson.annotations.SerializedName

data class MedicalRecordsResponse (
    @field:SerializedName("id")
    val medicalRecordId: Int,

    @field:SerializedName("Poli")
    val medicalRecordPoli: String,

    @field:SerializedName("date")
    val date: Int

)