package com.example.aplikasikonsultasidokter.network

import com.example.aplikasikonsultasidokter.model.*
import retrofit2.Call
import retrofit2.http.*

interface ApiClient {
    @FormUrlEncoded
    @POST("checkLogin.php")
    fun login(
        @Field("email") userEmail: String,
        @Field("password") password: String
    ): Call<ResponseLogin>

    @FormUrlEncoded
    @POST("createDataLogin.php")
    fun createData(
        @Field("nama") nama: String,
        @Field("jenis_kelamin") jenisKelamin: String,
        @Field("provinsi") provinsi: String,
        @Field("jalan") alamat1: String,
        @Field("kecamatan") alamat2: String,
        @Field("kabupaten_kota") alamat3: String,
        @Field("no_hp") noHp: String,
        @Field("email") email: String,
        @Field("password") password: String,
    ): Call<ResponseRegister>

    @GET("getDataDokter.php")
    fun dataDokter(): Call<ArrayList<DokterResponse>>

    @GET("getDataDokter.php")
    fun detailDokter(
        @Query("id") id: Int
    ): Call<DokterDetailResponse>

    @GET("getDataLogin.php")
    fun getProfile(
        @Query("email") email: String
    ): Call<MyProfileResponse>

    @FormUrlEncoded
    @POST("updatePassword.php")
    fun lupaPassword(
        @Field("email") userEmail: String,
        @Field("password") password: String
    ): Call<ResponseLogin>
}