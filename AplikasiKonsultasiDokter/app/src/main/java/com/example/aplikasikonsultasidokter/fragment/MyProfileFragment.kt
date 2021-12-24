package com.example.aplikasikonsultasidokter.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.example.aplikasikonsultasidokter.activity.HomeActivity
import com.example.aplikasikonsultasidokter.activity.LoginActivity
import com.example.aplikasikonsultasidokter.controller.MyProfileViewModel
import com.example.aplikasikonsultasidokter.databinding.FragmentMyProfileBinding

class MyProfileFragment : Fragment() {
    private var _binding: FragmentMyProfileBinding? = null
    private val binding get() = _binding!!

    companion object{
        const val EXTRA_LOGIN = "extra_login"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMyProfileBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val email = activity?.intent?.getStringExtra(HomeActivity.EXTRA_EMAIL)
        val bundle = Bundle()
        bundle.putString(EXTRA_LOGIN, email)

        val model = ViewModelProvider(this)[MyProfileViewModel::class.java]

        if(email != null) {
            model.setMyProfile(email)
        }

        model.getMyProfil().observe(viewLifecycleOwner) {
            if (it != null) {
                binding.apply {
                    Glide.with(this@MyProfileFragment)
                        .load("https://cdn.pixabay.com/photo/2015/10/05/22/37/blank-profile-picture-973460_1280.png")
                        .into(myPhotoProfile)
                    namaSaya.text = it.nama
                    jenisKelaminSaya.text = it.jenis_kelamin
                    alamat1.text = it.jalan
                    alamat2.text = it.kecamatan
                    kabupatenSaya.text = it.kabupaten_kota
                    provinsiSaya.text = it.provinsi
                    nomorSaya.text = it.no_hp
                    emailSaya.text = it.email
                }
            }
        }

        binding.btnLogout.setOnClickListener {
            val intent = Intent(activity, LoginActivity::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}