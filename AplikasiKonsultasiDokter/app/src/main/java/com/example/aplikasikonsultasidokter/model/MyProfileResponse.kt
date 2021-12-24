package com.example.aplikasikonsultasidokter.model

import com.google.gson.annotations.SerializedName

data class MyProfileResponse (
    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("nama")
    val nama: String?,

    @field:SerializedName("jenis_kelamin")
    val jenis_kelamin: String?,

    @field:SerializedName("provinsi")
    val provinsi: String?,

    @field:SerializedName("jalan")
    val jalan: String?,

    @field:SerializedName("kecamatan")
    val kecamatan: String?,

    @field:SerializedName("kabupaten_kota")
    val kabupaten_kota: String?,

    @field:SerializedName("no_hp")
    val no_hp: String?,

    @field:SerializedName("email")
    val email: String?,
)