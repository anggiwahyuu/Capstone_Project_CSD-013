package com.example.aplikasikonsultasidokter.fragment.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.aplikasikonsultasidokter.databinding.FragmentNotificationsBinding

class NotificationsFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentNotificationsBinding? = null
    private val binding get() = _binding!!
    private lateinit var nMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentNotificationsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        binding.map.onCreate(savedInstanceState)
        binding.map.getMapAsync(this)
        binding.map.onResume()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        nMap = googleMap
        // Add a marker in Sydney and move the camera
        val sydney = LatLng(-34.0, 151.0)
        nMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        nMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))
    }
}