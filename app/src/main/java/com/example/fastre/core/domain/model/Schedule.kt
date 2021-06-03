package com.example.fastre.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Schedule(
    val doctorId: Int,
    val doctorName: String,
    val doctorPoly: Int,
    val doctorPhoto: String,
    val doctorSchedule1: String,
    val doctorSchedule2: String,
    val doctorSchedule3: String,
): Parcelable