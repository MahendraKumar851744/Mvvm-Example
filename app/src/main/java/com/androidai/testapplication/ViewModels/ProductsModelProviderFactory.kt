package com.androidai.testapplication.ViewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.androidai.testapplication.repository.ProductsRepository

class ProductsModelProviderFactory(
    val productsRepository: ProductsRepository
) :ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ProductsViewModel(productsRepository) as T
    }
}