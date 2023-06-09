package com.androidai.testapplication.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.androidai.testapplication.Models.Product
import com.androidai.testapplication.R
import com.androidai.testapplication.databinding.FragProductDetailBinding
import com.androidai.testapplication.databinding.FragmentProductBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject



class ProductDetailFrament:Fragment() {



    private var _binding: FragProductDetailBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragProductDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            val product = bundle.getSerializable("product") as Product
            binding.tvTitle.text = product.title
            binding.tvDesc.text = product.description
            Glide.with(this).load(product.images.get(0)).into(binding.ivImage)
        }
        binding.ivBack.setOnClickListener(View.OnClickListener {
            navigateBack()
        })


    }
    fun navigateBack() {
        requireActivity().supportFragmentManager.popBackStack()
    }
}