package com.androidai.testapplication.util

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.androidai.testapplication.R

class Defaults {
    companion object{
        fun replaceFragment(fragmentManager:FragmentManager,fragment: Fragment) {
            val fragmentTransaction = fragmentManager.beginTransaction()
            fragmentTransaction.add(R.id.flFragment, fragment)
            fragmentTransaction.commit()
        }
    }
}