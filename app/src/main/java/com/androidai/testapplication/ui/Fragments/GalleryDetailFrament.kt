package com.androidai.testapplication.ui.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.androidai.testapplication.Models.Product
import com.androidai.testapplication.Models.galleryItem
import com.androidai.testapplication.R
import com.androidai.testapplication.databinding.FragGalleryDetailBinding
import com.androidai.testapplication.databinding.FragProductDetailBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class GalleryDetailFrament:Fragment(R.layout.frag_gallery_detail) {


    private var _binding: FragGalleryDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragGalleryDetailBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val bundle = arguments
        if (bundle != null) {
            val product = bundle.getSerializable("gallery_product") as galleryItem
            Glide.with(this).load(product.thumbnailUrl).into(binding.ivImage)
        }
        binding.ivBack.setOnClickListener(View.OnClickListener {
            navigateBack()
        })


    }
    fun navigateBack() {
        requireActivity().supportFragmentManager.popBackStack()
    }
}