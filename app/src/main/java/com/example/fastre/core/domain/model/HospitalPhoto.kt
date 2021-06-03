package com.example.fastre.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class HospitalPhoto(
    var photo: String ?= null
): Parcelable
