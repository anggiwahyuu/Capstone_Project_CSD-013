package com.example.aplikasikonsultasidokter.fragment.home

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.firebase.auth.FirebaseAuth
import com.example.aplikasikonsultasidokter.R
import com.example.aplikasikonsultasidokter.ui.activity.AuthActivity
import com.example.aplikasikonsultasidokter.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        auth = FirebaseAuth.getInstance()
        binding.btnLogout.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_logout -> {
                auth.signOut()
                startActivity(Intent(activity!!, AuthActivity::class.java))
                activity!!.finish()
            }
        }
    }
}