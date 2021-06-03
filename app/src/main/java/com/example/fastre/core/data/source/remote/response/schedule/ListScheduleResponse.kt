package com.example.fastre.core.data.source.remote.response.schedule

import com.google.gson.annotations.SerializedName

data class ListScheduleResponse(
    @field:SerializedName("schedules")
    val schedule: List<ScheduleResponse>
)
