package com.androidai.testapplication.api

import com.androidai.testapplication.Models.products
import retrofit2.Response
import retrofit2.http.GET

interface ProductsApi {

    @GET("products")
    suspend fun getProducts():Response<products>

}