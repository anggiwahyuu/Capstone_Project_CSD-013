package com.example.aplikasikonsultasidokter.ui.activity

import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.example.aplikasikonsultasidokter.R
import com.example.aplikasikonsultasidokter.databinding.ActivityMainBinding
import com.example.aplikasikonsultasidokter.fragment.home.HomeFragment
import com.example.aplikasikonsultasidokter.fragment.home.NotificationsFragment
import com.example.aplikasikonsultasidokter.fragment.home.ProfileFragment
import com.example.aplikasikonsultasidokter.ui.fragment.home.*

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var ft: FragmentTransaction
    private val fragment1 = HomeFragment()
    private val fragment2 = AlmanacFragment()
    private val fragment3 = AddFragment()
    private val fragment4 = NotificationsFragment()
    private val fragment5 = ProfileFragment()
    private val fm = supportFragmentManager
    var active: Fragment = fragment1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)

        binding.bottomNav.setOnNavigationItemSelectedListener(this)

        fm.beginTransaction().add(R.id.frame_main, fragment5, "5").hide(fragment5).commit()
        fm.beginTransaction().add(R.id.frame_main, fragment4, "4").hide(fragment4).commit()
        fm.beginTransaction().add(R.id.frame_main, fragment3, "3").hide(fragment3).commit()
        fm.beginTransaction().add(R.id.frame_main, fragment2, "2").hide(fragment2).commit()
        fm.beginTransaction().add(R.id.frame_main, fragment1, "1").commit()

        setContentView(binding.root)
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.nav_home -> {
                fm.beginTransaction().hide(active).show(fragment1).commit()
                active = fragment1
                return true
            }
            R.id.nav_almanac -> {
                fm.beginTransaction().hide(active).show(fragment2).commit()
                active = fragment2
                return true
            }
            R.id.nav_add -> {
                fm.beginTransaction().hide(active).show(fragment3).commit()
                active = fragment3
                return true
            }
            R.id.nav_notifications -> {
                fm.beginTransaction().hide(active).show(fragment4).commit()
                active = fragment4
                return true
            }
            R.id.nav_profile -> {
                fm.beginTransaction().hide(active).show(fragment5).commit()
                active = fragment5
                return true
            }
        }
        return false
    }
}