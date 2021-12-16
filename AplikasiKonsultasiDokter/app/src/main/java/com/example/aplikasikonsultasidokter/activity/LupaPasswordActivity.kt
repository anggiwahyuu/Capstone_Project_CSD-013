package com.example.aplikasikonsultasidokter.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.aplikasikonsultasidokter.databinding.ActivityLupaPasswordBinding
import com.example.aplikasikonsultasidokter.model.ResponseLogin
import com.example.aplikasikonsultasidokter.network.ApiRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LupaPasswordActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLupaPasswordBinding

    private var email: String = ""
    private var pass: String = ""
    private var confirm: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLupaPasswordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Lupa Password"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnSubmit.setOnClickListener {
            email = binding.email.text.toString()
            pass = binding.passBaru.text.toString()
            confirm = binding.konfirmasi.text.toString()

            when {
                email == "" -> {
                    binding.email.error = "Pastikan Anda Mengisi Email"
                }
                pass == "" -> {
                    binding.passBaru.error = "Pastikan Password Mengisi Email"
                }
                confirm == "" -> {
                    binding.konfirmasi.error = "Pastikan Anda Mengisi Password Kembali"
                }
                confirm != pass -> {
                    binding.konfirmasi.error = "Pastikan Konfirmasi Password Anda Sama Dengan Password yang Anda Masukkan"
                }
                else -> {
                    binding.loadingBar.visibility = View.VISIBLE
                    val api = ApiRetrofit.getApiClient().lupaPassword(email, pass)
                    api.enqueue(object: Callback<ResponseLogin> {
                        override fun onResponse(call: Call<ResponseLogin>, response: Response<ResponseLogin>) {
                            if (response.isSuccessful){
                                if(response.body()?.response == true){
                                    binding.loadingBar.visibility = View.INVISIBLE
                                    Toast.makeText(
                                        this@LupaPasswordActivity,
                                        "Password Berhasil Diubah, Silakan Gunakan Untuk Login",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    finish()
                                } else {
                                    binding.loadingBar.visibility = View.INVISIBLE
                                    Toast.makeText(
                                        this@LupaPasswordActivity,
                                        "Email Yang Anda Masukkan Salah",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            } else {
                                binding.loadingBar.visibility = View.INVISIBLE
                                Toast.makeText(
                                    this@LupaPasswordActivity,
                                    "Terjadi Kesalahan Yang Tidak Diketahui",
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
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}