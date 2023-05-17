package com.androidai.testapplication.Models

data class products(
    val limit: Int,
    val products: List<Product>,
    val skip: Int,
    val total: Int
)