package com.example.aplikasikonsultasidokter.controller

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory private constructor(private val mApplication: Application) : ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var INSTANCE: ViewModelFactory? = null
        @JvmStatic
        fun getInstance(application: Application): ViewModelFactory {
            if (INSTANCE == null) {
                synchronized(ViewModelFactory::class.java) {
                    INSTANCE = ViewModelFactory(application)
                }
            }
            return INSTANCE as ViewModelFactory
        }
    }

    override fun <T : ViewModel> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(DokterDetailViewModel::class.java)) {
            return DokterDetailViewModel() as T
        } else if(modelClass.isAssignableFrom(MyProfileViewModel::class.java)) {
            return MyProfileViewModel() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class: ${modelClass.name}")
    }
}