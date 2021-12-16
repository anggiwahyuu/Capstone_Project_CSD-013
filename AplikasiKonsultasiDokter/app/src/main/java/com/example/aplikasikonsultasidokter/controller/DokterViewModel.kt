package com.example.aplikasikonsultasidokter.controller

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.aplikasikonsultasidokter.model.DokterResponse
import com.example.aplikasikonsultasidokter.network.ApiRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DokterViewModel : ViewModel() {
    val listDokter = MutableLiveData<ArrayList<DokterResponse>>()

    fun findAllDokter() {
        val client = ApiRetrofit.getApiClient().dataDokter()
        client.enqueue(object : Callback<ArrayList<DokterResponse>> {
            override fun onResponse(
                call: Call<ArrayList<DokterResponse>>,
                response: Response<ArrayList<DokterResponse>>
            ) {
                if (response.isSuccessful) {
                    listDokter.postValue(response.body())
                } else {
                    Log.e(ContentValues.TAG, "onFailure: ${response.message()}")
                }
            }

            override fun onFailure(call: Call<ArrayList<DokterResponse>>, t: Throwable) {
                Log.e(ContentValues.TAG, "onFailure: ${t.message}")
            }
        })
    }

    fun getSearchUsers(): LiveData<ArrayList<DokterResponse>> {
        return listDokter
    }
}