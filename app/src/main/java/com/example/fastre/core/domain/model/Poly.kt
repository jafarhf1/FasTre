package com.example.fastre.core.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Poly(
    val polyId: Int,
    val polyName: String
): Parcelable