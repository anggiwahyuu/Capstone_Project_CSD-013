package com.example.aplikasikonsultasidokter.controller

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasikonsultasidokter.model.MyProfileResponse
import com.example.aplikasikonsultasidokter.network.ApiRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MyProfileViewModel : ViewModel() {
    val user = MutableLiveData<MyProfileResponse>()

    fun setMyProfile(email: String) {
        val client = ApiRetrofit.getApiClient().getProfile(email)
        client.enqueue(object : Callback<MyProfileResponse> {
            override fun onResponse(
                call: Call<MyProfileResponse>,
                response: Response<MyProfileResponse>
            ) {
                if (response.isSuccessful) {
                    user.postValue(response.body())
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<MyProfileResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getMyProfil(): LiveData<MyProfileResponse> {
        return user
    }
}