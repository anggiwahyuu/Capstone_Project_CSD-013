package com.example.aplikasikonsultasidokter.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.aplikasikonsultasidokter.databinding.ActivityLoginBinding
import com.example.aplikasikonsultasidokter.model.ResponseLogin
import com.example.aplikasikonsultasidokter.network.ApiRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private var user: String = ""
    private var pass: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        binding.btnSubmit.setOnClickListener{
            user = binding.userEmail.text.toString()
            pass = binding.userPassword.text.toString()

            when{
                user == "" -> {
                    binding.userEmail.error = "Mohon Isi Email Anda"
                }
                pass == "" -> {
                    binding.userPassword.error = "Mohon Isi Password Anda"
                }
                else -> {
                    binding.loadingBar.visibility = View.VISIBLE
                    val api = ApiRetrofit.getApiClient().login(user, pass)
                    api.enqueue(object : Callback<ResponseLogin>{
                        override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                            if(response.isSuccessful) {
                                if (response.body()?.response == true) {
                                    binding.loadingBar.visibility = View.INVISIBLE
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Login Berhasil dengan Email $user",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    val intent = Intent(this@LoginActivity, HomeActivity::class.java)
                                    intent.putExtra(HomeActivity.EXTRA_EMAIL, user)
                                    startActivity(intent)
                                    finish()
                                } else {
                                    binding.loadingBar.visibility = View.INVISIBLE
                                    Toast.makeText(
                                        this@LoginActivity,
                                        "Periksa kembali Email atau Password Anda",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            } else {
                                binding.loadingBar.visibility = View.INVISIBLE
                                Toast.makeText(
                                    this@LoginActivity,
                                    "Terjadi Kesalahan Dalam Login",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                        override fun onFailure(call: Call<ResponseLogin>, t: Throwable) {
                            Log.e("pesan error", "${t.message}")
                        }
                    })
                }
            }
        }

        binding.btnRegister.setOnClickListener{
            val intent = Intent(this@LoginActivity, RegisterActivity::class.java)
            startActivity(intent)
        }

        binding.btnLupaSandi.setOnClickListener {
            val intent = Intent(this@LoginActivity, LupaPasswordActivity::class.java)
            startActivity(intent)
        }
    }
}