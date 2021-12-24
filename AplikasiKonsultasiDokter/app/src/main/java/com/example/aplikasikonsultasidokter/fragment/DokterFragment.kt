package com.example.aplikasikonsultasidokter.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.aplikasikonsultasidokter.activity.DokterDetailActivity
import com.example.aplikasikonsultasidokter.adapter.DokterAdapter
import com.example.aplikasikonsultasidokter.controller.DokterViewModel
import com.example.aplikasikonsultasidokter.databinding.FragmentDokterBinding
import com.example.aplikasikonsultasidokter.model.DokterResponse

class DokterFragment : Fragment() {
    private var _binding: FragmentDokterBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDokterBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?){
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProvider(this)[DokterViewModel::class.java]

        binding.viewDoktor.setHasFixedSize(true)
        val layoutManager = LinearLayoutManager(activity)
        binding.viewDoktor.layoutManager = layoutManager
        val itemDecoration = DividerItemDecoration(activity, layoutManager.orientation)
        binding.viewDoktor.addItemDecoration(itemDecoration)

        val adapter = DokterAdapter()
        adapter.notifyDataSetChanged()
        binding.viewDoktor.adapter = adapter

        model.findAllDokter()

        model.getSearchUsers().observe(viewLifecycleOwner) {
            if (it == null) {
                showLoading(true)
            } else{
                adapter.setData(it)
                showLoading(false)
            }
        }

        adapter.setOnItemClickCallback(object : DokterAdapter.OnItemClickCallback {
            override fun onItemClicked(data: DokterResponse) {
                Intent(activity, DokterDetailActivity::class.java).also {
                    it.putExtra(DokterDetailActivity.EXTRA_NAME, data.nama)
                    it.putExtra(DokterDetailActivity.EXTRA_ID, data.id)
                    it.putExtra(DokterDetailActivity.EXTRA_SPECIALIST, data.spesialis)
                    it.putExtra(DokterDetailActivity.EXTRA_AVATAR, data.avatar)
                    startActivity(it)
                }
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun showLoading(isLoading: Boolean) {
        if (isLoading) {
            binding.progressBarDoctor.visibility = View.VISIBLE
        } else {
            binding.progressBarDoctor.visibility = View.INVISIBLE
        }
    }
}