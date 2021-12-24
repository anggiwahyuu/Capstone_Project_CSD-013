package com.example.aplikasikonsultasidokter.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.aplikasikonsultasidokter.controller.DokterDetailViewModel
import com.example.aplikasikonsultasidokter.controller.ViewModelFactory
import com.example.aplikasikonsultasidokter.databinding.ActivityDokterDetailBinding

class DokterDetailActivity : AppCompatActivity() {
    companion object{
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_ID = "extra_id"
        const val EXTRA_SPECIALIST = "extra_specialist"
        const val EXTRA_AVATAR = "extra_avatar"
    }

    private lateinit var binding: ActivityDokterDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDokterDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "User Detail"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val model = obtainViewModel(this@DokterDetailActivity)

        val bundle = Bundle()

        val id = intent.getIntExtra(EXTRA_ID, 0)
        bundle.putInt(EXTRA_ID, id)

        val name = intent.getStringExtra(EXTRA_NAME)
        bundle.putString(EXTRA_NAME, name)

        val avatar = intent.getStringExtra(EXTRA_AVATAR)
        bundle.putString(EXTRA_AVATAR, avatar)

        supportActionBar?.elevation = 0f

        model.setUserDetail(id)

        model.getUserDetail().observe(this) {
            if (it != null) {
                binding.apply {
                    Glide.with(this@DokterDetailActivity)
                        .load(it.avatar)
                        .into(doctorPhotoProfile)
                    namaDokter.text = it.nama
                    spesialisDokter.text = it.spesialis
                    jenisKelaminDokter.text = it.jenis_kelamin
                    tempatPraktikDokter.text = it.tempat_praktik
                    alamatDokter.text = it.alamat_tempat_praktik
                    kabupatenDokter.text = it.kabupaten_kota
                    provinsiDokter.text = it.provinsi
                    nomorDokter.text = it.no_hp
                    mapsDokter.text = it.link_lokasi_maps.toString()
                    keteranganDokter.text = it.keterangan
                }
            }
        }

        binding.btnChatNow.setOnClickListener {
            val intent = Intent(this@DokterDetailActivity, ChatRoomActivity::class.java)
            intent.putExtra(ChatRoomActivity.EXTRA_ID, id)
            intent.putExtra(ChatRoomActivity.EXTRA_PHOTO, avatar)
            intent.putExtra(ChatRoomActivity.EXTRA_NAME, name)
            startActivity(intent)
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }

    private fun obtainViewModel(activity: AppCompatActivity): DokterDetailViewModel {
        val factory = ViewModelFactory.getInstance(activity.application)
        return ViewModelProvider(activity, factory)[DokterDetailViewModel::class.java]
    }
}