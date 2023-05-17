package com.androidai.testapplication.ui.Fragments

import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.androidai.testapplication.Models.Product
import com.androidai.testapplication.R
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import javax.inject.Inject



class ProductDetailFrament:Fragment(R.layout.frag_product_detail) {

    private lateinit var iv_image:ImageView
    private lateinit var tv_title:TextView
    private lateinit var tv_desc:TextView
    private lateinit var iv_back:ImageView

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        iv_image = view.findViewById(R.id.iv_image)
        tv_desc = view.findViewById(R.id.tv_desc)
        tv_title = view.findViewById(R.id.tv_title)
        iv_back = view.findViewById(R.id.iv_back)
        val bundle = arguments
        if (bundle != null) {
            val product = bundle.getSerializable("product") as Product
            tv_title.text = product.title
            tv_desc.text = product.description
            Glide.with(this).load(product.images.get(0)).into(iv_image)
        }
        iv_back.setOnClickListener(View.OnClickListener {
            navigateBack()
        })


    }
    fun navigateBack() {
        requireActivity().supportFragmentManager.popBackStack()
    }
}