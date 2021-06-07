package com.example.fastre.core.data.source.remote.response.polyclinic

import com.google.gson.annotations.SerializedName

data class PolyclinicResponse(
    @field:SerializedName("id")
    val polyId: Int,

    @field:SerializedName("polyName")
    val polyName: String,

    @field:SerializedName("currentNumber")
    val currentNumber: Int
)
