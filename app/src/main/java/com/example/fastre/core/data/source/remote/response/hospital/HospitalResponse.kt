package com.example.fastre.core.data.source.remote.response.hospital

import com.google.gson.annotations.SerializedName

data class HospitalResponse(
    @field:SerializedName("id")
    val hospitalId: Int,

    @field:SerializedName("name")
    val hospitalName: String,

    @field:SerializedName("telephoneNum")
    val hospitalPhone: String,

    @field:SerializedName("phoneNum")
    val hospitalWhatsapp: String,

    @field:SerializedName("longitude")
    val longitude: Double,

    @field:SerializedName("latitude")
    val latitude: Double,

    @field:SerializedName("photo1")
    val hospitalPhoto1: String,

    @field:SerializedName("photo2")
    val hospitalPhoto2: String,

    @field:SerializedName("photo3")
    val hospitalPhoto3: String
)