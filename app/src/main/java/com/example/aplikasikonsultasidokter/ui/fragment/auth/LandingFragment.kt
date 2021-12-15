package com.example.aplikasikonsultasidokter.ui.fragment.auth

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import com.example.aplikasikonsultasidokter.R
import com.example.aplikasikonsultasidokter.databinding.FragmentLandingBinding

class LandingFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentLandingBinding? = null
    private val binding get() = _binding!!
    private lateinit var ft: FragmentTransaction

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLandingBinding.inflate(inflater, container, false)

        ft = activity!!.supportFragmentManager.beginTransaction()

        changeColorText()

        binding.btnLogin.setOnClickListener(this)
        binding.btnRegister.setOnClickListener(this)
        return binding.root
    }

    private fun changeColorText(){
        val spannable = SpannableString(binding.tvLanding.text.toString())
        spannable.setSpan(
            R.color.primaryDark, 0, 9, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.tvLanding.text = spannable
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_login ->
                ft.replace(R.id.frame_auth, LoginFragment())
                    .addToBackStack(null)
                    .commit()
            R.id.btn_register ->
                ft.replace(R.id.frame_auth, RegisterFragment())
                    .addToBackStack(null)
                    .commit()
        }
    }
}