package com.example.fastre.core.data.source.remote.response.medicalRecords

import com.google.gson.annotations.SerializedName

data class ListMedicalRecordsResponse (
    @field:SerializedName("queues")
    val medicalRecords: List<MedicalRecordsResponse>
)