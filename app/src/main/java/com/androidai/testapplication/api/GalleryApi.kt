package com.androidai.testapplication.api

import com.androidai.testapplication.Models.gallery
import retrofit2.Response
import retrofit2.http.GET

interface GalleryApi {
    @GET("photos")
    suspend fun getGallery(): Response<gallery>

}