package com.example.fastre.core.data.source.remote.response.schedule

import com.google.gson.annotations.SerializedName

data class ScheduleResponse(
    @field:SerializedName("doctorId")
    val doctorId: Int,

    @field:SerializedName("doctorName")
    val doctorName: String,

    @field:SerializedName("polyId")
    val doctorPoly: Int,

    @field:SerializedName("imgURL")
    val doctorPhoto: String,

    @field:SerializedName("schedule1")
    val doctorSchedule1: String,

    @field:SerializedName("schedule2")
    val doctorSchedule2: String,

    @field:SerializedName("schedule3")
    val doctorSchedule3: String
)
