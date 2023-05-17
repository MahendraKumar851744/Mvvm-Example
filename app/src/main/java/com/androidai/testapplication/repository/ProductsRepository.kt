package com.androidai.testapplication.repository

import com.androidai.testapplication.Retrofit.GalleryRetrofitInstance
import com.androidai.testapplication.Retrofit.RetrofitInstance

class ProductsRepository {
    suspend fun getProducts() = RetrofitInstance.api.getProducts()
    suspend fun getGallery() = GalleryRetrofitInstance.api.getGallery()
}