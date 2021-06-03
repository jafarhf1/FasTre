package com.example.fastre.core.data.source.remote.response.hospital

import com.google.gson.annotations.SerializedName

data class ListHospitalResponse(
    @field:SerializedName("hospitals")
    val hospital: List<HospitalResponse>
)