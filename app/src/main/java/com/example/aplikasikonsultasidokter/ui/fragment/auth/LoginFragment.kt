package com.example.aplikasikonsultasidokter.ui.fragment.auth

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.firebase.auth.FirebaseAuth
import com.example.aplikasikonsultasidokter.R
import com.example.aplikasikonsultasidokter.ui.activity.MainActivity
import com.example.aplikasikonsultasidokter.databinding.FragmentLoginBinding

class LoginFragment : Fragment(), View.OnClickListener {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!
    private lateinit var auth: FirebaseAuth
    private lateinit var ft: FragmentTransaction

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        auth = FirebaseAuth.getInstance()
        ft = activity!!.supportFragmentManager.beginTransaction()

        binding.btnLogin.setOnClickListener(this)
        binding.tvForgotPass.setOnClickListener(this)
        return binding.root
    }

    override fun onClick(v: View) {
        when(v.id){
            R.id.btn_login ->
                loginUser()
            R.id.tv_forgot_pass ->
                ft.replace(R.id.frame_auth, ForgotPassFragment())
                    .addToBackStack(null)
                    .commit()
        }
    }

    private fun loginUser(){
        binding.progress.visibility = View.VISIBLE
        val email = binding.tilEmail.editText?.text.toString()
        val pass = binding.tilPass.editText?.text.toString()

        if (!isValid(email, pass)){
            binding.progress.visibility = View.GONE
        } else {
            auth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                if (it.isSuccessful){
                    Toast.makeText(activity!!, "Welcome to Halo-Pet!", Toast.LENGTH_SHORT).show()
                    startActivity(Intent(activity!!, MainActivity::class.java))
                    activity!!.finish()
                } else {
                    Toast.makeText(activity!!, "Failed to Login", Toast.LENGTH_SHORT).show()
                }
                binding.progress.visibility = View.GONE
            }.addOnFailureListener {
                Toast.makeText(activity!!, it.message, Toast.LENGTH_SHORT).show()
                binding.progress.visibility = View.GONE
            }
        }
    }

    private fun isValid(email : String, pass: String) : Boolean {
        return if (email.isEmpty() || pass.isEmpty()){
            binding.tilEmail.error = "Empty Field"
            binding.tilPass.error = "Empty Field"
            false
        } else {
            binding.tilEmail.error = null
            binding.tilPass.error = null

            binding.tilEmail.isErrorEnabled
            binding.tilPass.isErrorEnabled
            true
        }
    }
}