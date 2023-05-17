package com.androidai.testapplication.Retrofit

import com.androidai.testapplication.api.GalleryApi
import com.androidai.testapplication.util.Constants.Companion.GALLERY_URL
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class GalleryRetrofitInstance {
    companion object{
        private val retrofit by lazy {
            val logging = HttpLoggingInterceptor()
            logging.setLevel(HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
                .addInterceptor(logging)
                .build()

            Retrofit.Builder()
                .client(client)
                .baseUrl(GALLERY_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        }

        val api by lazy{
            retrofit.create(GalleryApi::class.java)
        }
    }
}