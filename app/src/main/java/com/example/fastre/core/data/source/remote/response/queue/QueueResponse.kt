package com.example.fastre.core.data.source.remote.response.queue

import com.google.gson.annotations.SerializedName

data class QueueResponse(
    @field:SerializedName("queueId")
    val queueId: String,

    @field:SerializedName("polyId")
    var queuePolyId: String,

    @field:SerializedName("number")
    var queueUserNumber: Int,

    @field:SerializedName("status")
    var queueStatus: String,

    @field:SerializedName("date")
    var queueDate: QueueDate,

    @field:SerializedName("estimatedTime")
    var estimatedTime: Double
)

data class QueueDate(
    @field:SerializedName("_seconds")
    var _seconds: Long,

    @field:SerializedName("_nanoseconds")
    var _nanoseconds: Long
)
