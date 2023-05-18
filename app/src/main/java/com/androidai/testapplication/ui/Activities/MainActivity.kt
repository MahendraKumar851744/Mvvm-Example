package com.androidai.testapplication.ui.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androidai.testapplication.R
import com.androidai.testapplication.repository.ProductsRepository
import com.androidai.testapplication.ui.Fragments.GalleryFragment
import com.androidai.testapplication.ui.Fragments.ProductFragment
import com.androidai.testapplication.ui.Fragments.TodoFragment
import com.androidai.testapplication.ViewModels.ProductsViewModel
import com.androidai.testapplication.ViewModels.ProductsModelProviderFactory
import com.androidai.testapplication.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.androidai.testapplication.util.Defaults.Companion.replaceFragment
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ProductsViewModel
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        val repository = ProductsRepository()
        val viewModelProviderFactory = ProductsModelProviderFactory(repository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(ProductsViewModel::class.java)
        val fragmentManager = supportFragmentManager
        replaceFragment(fragmentManager,ProductFragment())
        binding.ll.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home-> {
                    replaceFragment(fragmentManager,ProductFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.gallery -> {
                    replaceFragment(fragmentManager,GalleryFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.todo ->{
                    replaceFragment(fragmentManager,TodoFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                else -> return@setOnNavigationItemSelectedListener false
            }
        }

    }

}