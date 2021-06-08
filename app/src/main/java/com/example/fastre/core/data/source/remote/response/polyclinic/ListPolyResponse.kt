package com.example.fastre.core.data.source.remote.response.polyclinic

import com.google.gson.annotations.SerializedName

data class ListPolyResponse(
    @field:SerializedName("polyclinics")
    val poly: List<PolyclinicResponse>
)
