package com.example.aplikasikonsultasidokter.network

import com.example.aplikasikonsultasidokter.model.ResponseCreateRoom
import com.example.aplikasikonsultasidokter.model.ResponseRegister
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

interface ApiClientChat {
    @FormUrlEncoded
    @POST("sendChatRoom.php")
    fun createDataChatRoom(
        @Field("name") namaDokter: String,
    ): Call<ResponseCreateRoom>

    @FormUrlEncoded
    @POST("sendChat.php")
    fun createDataChatRoomMessage(
        @Field("chat_room_id") chatRoomId: String,
        @Field("user_id") userId: String,
        @Field("message") message: String,
    ): Call<ResponseRegister>

//    @GET("getDataChatRoom.php")
//    fun dataChatRoom(): Call<ArrayList<RoomResponse>>
//
//    @GET("getDataChat.php")
//    fun dataDataChat(): Call<ArrayList<ChatResponse>>
}