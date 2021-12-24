package com.example.aplikasikonsultasidokter.model

import com.google.gson.annotations.SerializedName

data class ResponseCreateRoom (
    @field:SerializedName("chat_room_id")
    val id: Int
)