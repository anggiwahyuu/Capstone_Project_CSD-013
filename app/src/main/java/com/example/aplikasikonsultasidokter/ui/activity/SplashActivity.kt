package com.example.aplikasikonsultasidokter.ui.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.google.firebase.auth.FirebaseAuth
import com.example.aplikasikonsultasidokter.R
import com.example.aplikasikonsultasidokter.databinding.ActivitySplashBinding

class SplashActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySplashBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(binding.root)

        checkSession()
    }

    private fun checkSession(){
        val user = FirebaseAuth.getInstance().currentUser
        Handler(Looper.myLooper()!!).postDelayed({
            if (user != null){
                startActivity(Intent(applicationContext, MainActivity::class.java))
            } else {
                startActivity(Intent(applicationContext, AuthActivity::class.java))
            }
            finish()
        }, 2000)
    }
}