package com.example.fastre.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MedicalRecords(
        val recordDate: Int
): Parcelable