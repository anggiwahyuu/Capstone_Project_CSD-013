package com.example.aplikasikonsultasidokter.adapter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.aplikasikonsultasidokter.fragment.ChatFragment
import com.example.aplikasikonsultasidokter.fragment.DokterFragment
import com.example.aplikasikonsultasidokter.fragment.MyProfileFragment

class SectionPagerAdapter(activity: AppCompatActivity) : FragmentStateAdapter(activity) {

    //private var fragmentBundle: Bundle = data

    override fun createFragment(position: Int): Fragment {
        var fragment: Fragment? = null
        when (position) {
            0 -> fragment = DokterFragment()
            1 -> fragment = ChatFragment()
            2 -> fragment = MyProfileFragment()
        }
        //fragment?.arguments = this.fragmentBundle
        return fragment as Fragment
    }

    override fun getItemCount(): Int {
        return 3
    }
}