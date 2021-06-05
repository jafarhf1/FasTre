package com.example.fastre.core.data.source.remote.response.medicalRecords

import com.google.gson.annotations.SerializedName

data class MedicalRecordsResponse (
    @field:SerializedName("date")
    var recordDate: Int,
)