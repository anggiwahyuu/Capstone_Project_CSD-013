package com.example.aplikasikonsultasidokter.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DokterResponse(
    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("nama")
    val nama: String?,

    @field:SerializedName("spesialis")
    val spesialis: String?,

    @field:SerializedName("avatar")
    val avatar: String?
): Parcelable