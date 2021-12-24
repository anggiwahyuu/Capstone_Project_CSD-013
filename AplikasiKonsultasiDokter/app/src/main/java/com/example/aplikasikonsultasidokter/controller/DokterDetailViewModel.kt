package com.example.aplikasikonsultasidokter.controller

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasikonsultasidokter.model.DokterDetailResponse
import com.example.aplikasikonsultasidokter.network.ApiRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DokterDetailViewModel : ViewModel() {
    val user = MutableLiveData<DokterDetailResponse>()

    fun setUserDetail(id: Int) {
        val client = ApiRetrofit.getApiClient().detailDokter(id)
        client.enqueue(object : Callback<DokterDetailResponse> {
            override fun onResponse(
                call: Call<DokterDetailResponse>,
                response: Response<DokterDetailResponse>
            ) {
                if (response.isSuccessful) {
                    user.postValue(response.body())
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<DokterDetailResponse>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getUserDetail(): LiveData<DokterDetailResponse> {
        return user
    }
}