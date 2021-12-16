package com.example.aplikasikonsultasidokter.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.aplikasikonsultasidokter.R
import com.example.aplikasikonsultasidokter.databinding.ActivityChatRoomBinding

class ChatRoomActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_ID = "extra_id"
        const val EXTRA_PHOTO = "extra_photo"
        const val EXTRA_NAME = "extra_name"
    }

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

        setData()
    }

    private fun setData(){
        val bundle = Bundle()

        val name = intent.getStringExtra(EXTRA_NAME)
        bundle.putString(EXTRA_NAME, name)

        val photo = intent.getStringExtra(EXTRA_PHOTO)
        bundle.putString(EXTRA_PHOTO, photo)

        binding.namaDokterToolbar.text = name.toString()
        Glide.with(this)
            .load(photo)
            .apply(RequestOptions())
            .into(binding.profileChatToolbar)
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}