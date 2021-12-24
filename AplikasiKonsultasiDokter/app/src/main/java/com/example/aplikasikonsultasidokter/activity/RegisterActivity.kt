package com.example.aplikasikonsultasidokter.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.aplikasikonsultasidokter.databinding.ActivityRegisterBinding
import com.example.aplikasikonsultasidokter.model.ResponseRegister
import com.example.aplikasikonsultasidokter.network.ApiRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private var nama: String = ""
    private var jenisKelamin: String = ""
    private var provinsi: String = ""
    private var jalan: String = ""
    private var kecamatan: String = ""
    private var kabupatenKota: String = ""
    private var noHp: String = ""
    private var email: String = ""
    private var password: String = ""
    private var confirm: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Registrasi"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        binding.btnRegisterActivity.setOnClickListener {
            nama = binding.registerNama.text.toString()
            jenisKelamin = binding.registerJenisKelamin.text.toString()
            provinsi = binding.registerProvinsi.text.toString()
            jalan = binding.registerAlamat1.text.toString()
            kecamatan = binding.registerAlamat2.text.toString()
            kabupatenKota = binding.registerAlamat3.text.toString()
            noHp = binding.registerNomor.text.toString()
            email = binding.registerEmail.text.toString()
            password = binding.registerPassword.text.toString()
            confirm = binding.registerConfirm.text.toString()

            when{
                nama == "" -> {
                    binding.registerNama.error = "Pastikan Nama Anda Tidak Kosong"
                }
                jenisKelamin == "" -> {
                    binding.registerJenisKelamin.error = "Pastikan Jenis Kelamin Anda Tidak Kosong"
                }
                provinsi == "" -> {
                    binding.registerProvinsi.error = "Pastikan Provinsi Anda Tidak Kosong"
                }
                jalan == "" -> {
                    binding.registerAlamat1.error = "Pastikan Alamat Anda Tidak Kosong"
                }
                kecamatan == "" -> {
                    binding.registerAlamat2.error = "Pastikan Alamat Anda Tidak Kosong"
                }
                kabupatenKota == "" -> {
                    binding.registerAlamat3.error = "Pastikan Alamat Anda Tidak Kosong"
                }
                noHp == "" -> {
                    binding.registerNomor.error = "Pastikan Nomor Telepon Anda Tidak Kosong"
                }
                email == "" -> {
                    binding.registerEmail.error = "Pastikan Email Anda Tidak Kosong"
                }
                password == "" -> {
                    binding.registerPassword.error = "Pastikan Password Anda Tidak Kosong"
                }
                confirm == "" -> {
                    binding.registerConfirm.error = "Pastikan Konfirmasi Password Anda Tidak Kosong"
                }
                confirm != password -> {
                    binding.registerConfirm.error = "Pastikan Konfirmasi Password Anda Sama Dengan Password yang Anda Masukkan"
                }
                else -> {
                    binding.loadingBar.visibility = View.VISIBLE
                    val api = ApiRetrofit.getApiClient()
                    api.createData (
                        nama,
                        jenisKelamin,
                        provinsi,
                        jalan,
                        kecamatan,
                        kabupatenKota,
                        noHp,
                        email,
                        password
                    ).enqueue(object: Callback<ResponseRegister>{
                        override fun onResponse(call: Call<ResponseRegister>, response: Response<ResponseRegister>) {
                            if (response.isSuccessful){
                                if(response.body()?.response == true) {
                                    binding.loadingBar.visibility = View.INVISIBLE
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        "Registrasi Berhasil",
                                        Toast.LENGTH_LONG
                                    ).show()
                                    finish()
                                } else {
                                    binding.loadingBar.visibility = View.INVISIBLE
                                    binding.registerEmail.error = "Silakan Ganti Email"
                                    Toast.makeText(
                                        this@RegisterActivity,
                                        "Email Yang Anda Gunakan Sudah Ada",
                                        Toast.LENGTH_LONG
                                    ).show()
                                }
                            } else {
                                binding.loadingBar.visibility = View.INVISIBLE
                                Toast.makeText(
                                    this@RegisterActivity,
                                    "Registrasi Gagal",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }
                        override fun onFailure(call: Call<ResponseRegister>, t: Throwable) {
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