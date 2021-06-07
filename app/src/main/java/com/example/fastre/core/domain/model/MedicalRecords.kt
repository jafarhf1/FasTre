package com.example.fastre.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MedicalRecords(
        var medicalDate: String,
        var medicalPolyId: String,
        var medicalUserNumber: Int?,
        var medicalQueueId: String?
): Parcelable