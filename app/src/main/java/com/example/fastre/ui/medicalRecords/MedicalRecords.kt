package com.example.fastre.ui.medicalRecords

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class MedicalRecords(
        val medicalRecordId: Int,
        val medicalRecordPoli: String,
        val date: Int

): Parcelable