package com.androidai.testapplication.Models

import java.io.Serializable

data class galleryItem(
    val albumId: Int,
    val id: Int,
    val thumbnailUrl: String,
    val title: String,
    val url: String
):Serializable