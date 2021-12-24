package com.example.aplikasikonsultasidokter.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.viewpager2.widget.ViewPager2
import com.example.aplikasikonsultasidokter.R
import com.example.aplikasikonsultasidokter.adapter.SectionPagerAdapter
import com.example.aplikasikonsultasidokter.databinding.ActivityHomeBinding
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator

class HomeActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_EMAIL = "extra_email"

        private val TAB_TITLES = intArrayOf(
            R.string.dokter,
            R.string.chat,
            R.string.akun_saya
        )
    }

    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val email = intent.getStringExtra(EXTRA_EMAIL)
        val bundle = Bundle()
        bundle.putString(EXTRA_EMAIL, email)

        val sectionsPagerAdapter = SectionPagerAdapter(this)
        val viewPager: ViewPager2 = findViewById(R.id.view_pager)
        viewPager.adapter = sectionsPagerAdapter
        val tabs: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabs, viewPager) { tab, position ->
            tab.text = resources.getString(TAB_TITLES[position])
        }.attach()
    }
}