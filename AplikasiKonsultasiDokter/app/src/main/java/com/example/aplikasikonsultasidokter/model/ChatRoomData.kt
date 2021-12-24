package com.example.aplikasikonsultasidokter.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ChatRoomData (
    var name: String?,
    var avatar: String?
): Parcelable