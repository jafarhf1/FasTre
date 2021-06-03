package com.example.fastre.core.data.source.remote.response.queue

import com.google.gson.annotations.SerializedName

data class QueueResponse(
    @field:SerializedName("date")
    val queueDate: Int,

    @field:SerializedName("userId")
    val queueId: String,

    //@field:SerializedName("userPolyId")
    //val queuePolyId: Int,

    @field:SerializedName("scheduledHour")
    val queueHour: Int,

    @field:SerializedName("scheduledMinute")
    val queueMinute: Int,
)
