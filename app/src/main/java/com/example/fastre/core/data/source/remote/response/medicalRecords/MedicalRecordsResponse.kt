package com.example.fastre.core.data.source.remote.response.medicalRecords

import com.google.gson.annotations.SerializedName

data class MedicalRecordsResponse (
    @field:SerializedName("date")
    var medicalDate: MedicalDate,

    @field:SerializedName("polyId")
    var medicalPolyId: Int,

    @field:SerializedName("number")
    var medicalUserNumber: Int,

    @field:SerializedName("queueId")
    var medicalQueueId: String
)

data class MedicalDate(
    @field:SerializedName("_seconds")
    var _seconds: Long,

    @field:SerializedName("_nanoseconds")
    var _nanoseconds: Long
)