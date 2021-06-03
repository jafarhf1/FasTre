package com.example.fastre.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Hospital(
    val hospitalId: Int,
    val hospitalName: String,
    val hospitalPhone: String,
    val hospitalWhatsapp: String,
    val hospitalLongitude: Double,
    val hospitalLatitude: Double,
    val hospitalPhoto1: String,
    val hospitalPhoto2: String,
    val hospitalPhoto3: String
): Parcelable