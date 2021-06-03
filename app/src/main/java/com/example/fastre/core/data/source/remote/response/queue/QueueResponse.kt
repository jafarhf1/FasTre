package com.example.fastre.core.data.source.remote.response.queue

import com.google.gson.annotations.SerializedName

data class QueueResponse(
    @field:SerializedName("date")
<<<<<<< HEAD
    val queueDate: Int,
=======
    val queueDate: String,
>>>>>>> 1b4e93ee8e8347f5bd5755ee1a8ec13012d2dd42

    @field:SerializedName("userId")
    val queueId: String,

<<<<<<< HEAD
    //@field:SerializedName("userPolyId")
    //val queuePolyId: Int,

=======
>>>>>>> 1b4e93ee8e8347f5bd5755ee1a8ec13012d2dd42
    @field:SerializedName("scheduledHour")
    val queueHour: Int,

    @field:SerializedName("scheduledMinute")
    val queueMinute: Int,
)
