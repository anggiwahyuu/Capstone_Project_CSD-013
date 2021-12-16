package com.example.aplikasikonsultasidokter.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class DokterDetailResponse (
    @field:SerializedName("id")
    val id: Int?,

    @field:SerializedName("nama")
    val nama: String?,

    @field:SerializedName("jenis_kelamin")
    val jenis_kelamin: String?,

    @field:SerializedName("spesialis")
    val spesialis: String?,

    @field:SerializedName("tempat_praktik")
    val tempat_praktik: String?,

    @field:SerializedName("alamat_tempat_praktik")
    val alamat_tempat_praktik: String?,

    @field:SerializedName("kabupaten_kota")
    val kabupaten_kota: String?,

    @field:SerializedName("provinsi")
    val provinsi: String?,

    @field:SerializedName("no_hp")
    val no_hp: String?,

    @field:SerializedName("keterangan")
    val keterangan: String?,

    @field:SerializedName("link_lokasi_map")
    val link_lokasi_maps: String?,

    @field:SerializedName("avatar")
    val avatar: String?
): Parcelable