package com.androidai.testapplication.Activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.androidai.testapplication.R
import com.androidai.testapplication.repository.ProductsRepository
import com.androidai.testapplication.ui.Fragments.GalleryFragment
import com.androidai.testapplication.ui.Fragments.ProductFragment
import com.androidai.testapplication.ui.Fragments.TodoFragment
import com.androidai.testapplication.ViewModels.ProductsViewModel
import com.androidai.testapplication.ViewModels.ProductsModelProviderFactory
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    lateinit var viewModel: ProductsViewModel
    private lateinit var bottomNavigationView: BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNavigationView = findViewById(R.id.ll);

        val repository = ProductsRepository()
        val viewModelProviderFactory = ProductsModelProviderFactory(repository)
        viewModel = ViewModelProvider(this,viewModelProviderFactory).get(ProductsViewModel::class.java)

        replaceFragment(ProductFragment())
        bottomNavigationView.setOnNavigationItemSelectedListener { item ->
            when (item.itemId) {
                R.id.home-> {
                    replaceFragment(ProductFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                R.id.gallery -> {
                    replaceFragment(GalleryFragment())
                    return@setOnNavigationItemSelectedListener true
                }

                R.id.todo ->{

                    replaceFragment(TodoFragment())
                    return@setOnNavigationItemSelectedListener true
                }
                // Add more cases for other menu items if needed
                else -> return@setOnNavigationItemSelectedListener false
            }
        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.flFragment, fragment)
        fragmentTransaction.commit()
    }


}