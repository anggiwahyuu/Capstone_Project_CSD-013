package com.example.aplikasikonsultasidokter.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.aplikasikonsultasidokter.R
import com.example.aplikasikonsultasidokter.databinding.ActivityChatRoomBinding
import com.example.aplikasikonsultasidokter.model.ResponseCreateRoom
import com.example.aplikasikonsultasidokter.model.ResponseRegister
import com.example.aplikasikonsultasidokter.network.ApiRetrofit
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ChatRoomActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_NAME = "extra_name"
    }

    private var message: String = ""
    private var namaDokter: String = ""
    private var idUser: String = ""
    private var ChatRomId: String = ""

    private lateinit var binding: ActivityChatRoomBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatRoomBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.hide()

        val bundle = Bundle()

        val id = intent.getIntExtra(EXTRA_ID,0)
        bundle.putInt(EXTRA_ID, id)

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.btnEnter.setOnClickListener {
            message = binding.chatInput.text.toString()

            val api = ApiRetrofit.getApiClientChat()
            api.createDataChatRoom (
                    namaDokter
            ).enqueue(object: Callback<ResponseCreateRoom> {
            override fun onResponse(call: Call<ResponseCreateRoom>, response: Response<ResponseCreateRoom>) {
                if (response.isSuccessful){
                    if(response.body()?.id != 0) {
                        finish()
                    } else {
                        Toast.makeText(
                            this@ChatRoomActivity,
                            "Email Yang Anda Gunakan Sudah Ada",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                } else {
                    Toast.makeText(
                        this@ChatRoomActivity,
                        "Registrasi Gagal",
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
            override fun onFailure(call: Call<ResponseCreateRoom>, t: Throwable) {
                Log.e("pesan error", "${t.message}")
            }
        })

            api.createDataChatRoomMessage (
                ChatRomId,
                idUser,
                message,
            ).enqueue(object: Callback<ResponseRegister> {
                override fun onResponse(call: Call<ResponseRegister>, response: Response<ResponseRegister>) {
                    if (response.isSuccessful){
                        if(response.body()?.response == true) {
                            finish()
                        } else {
                            Toast.makeText(
                                this@ChatRoomActivity,
                                "Email Yang Anda Gunakan Sudah Ada",
                                Toast.LENGTH_LONG
                            ).show()
                        }
                    } else {
                        Toast.makeText(
                            this@ChatRoomActivity,
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

        setData()
    }

    private fun setData(){
        val bundle = Bundle()

        val name = intent.getStringExtra(EXTRA_NAME)
        bundle.putString(EXTRA_NAME, name)
        namaDokter = name.toString()
        val photo = intent.getStringExtra(EXTRA_PHOTO)
        bundle.putString(EXTRA_PHOTO, photo)

        binding.namaDokterToolbar.text = name.toString()
        Glide.with(this)
            .load(photo)
            .apply(RequestOptions())
            .into(binding.profileChatToolbar)
    }

    private fun setDataChat(){

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}