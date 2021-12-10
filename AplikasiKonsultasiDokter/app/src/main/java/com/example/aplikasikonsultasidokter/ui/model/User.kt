package com.example.aplikasikonsultasidokter.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class User (
    val Email: String,
    val Name: String?,
    val pass: String?,
    val confirm: String?
) :Parcelable