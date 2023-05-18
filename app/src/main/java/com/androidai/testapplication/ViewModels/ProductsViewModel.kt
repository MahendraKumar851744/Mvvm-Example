package com.androidai.testapplication.ViewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.androidai.testapplication.Models.gallery
import com.androidai.testapplication.Models.products
import com.androidai.testapplication.repository.ProductsRepository
import com.androiddevs.mvvmnewsapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import retrofit2.Response
class ProductsViewModel(
    val productsRepository: ProductsRepository

):ViewModel() {
     val products:MutableLiveData<Resource<products>> = MutableLiveData()
     val gallery:MutableLiveData<Resource<gallery>> = MutableLiveData()

    init {
        getProducts()
        getGallery()
    }

    fun getProducts() = viewModelScope.launch {
        products.postValue(Resource.Loading())
        val response = productsRepository.getProducts()
        products.postValue(handleProductsResponse(response))
    }

    fun getGallery() = viewModelScope.launch {
        gallery.postValue(Resource.Loading())
        val response = productsRepository.getGallery()
        gallery.postValue(handleGalleryResponse(response))
    }

    private fun handleProductsResponse(response: Response<products>): Resource<products>? {
        if(response.isSuccessful){
            response?.body()?.let {

                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }

    private fun handleGalleryResponse(response: Response<gallery>): Resource<gallery>? {
        if(response.isSuccessful){
            response?.body()?.let {

                return Resource.Success(it)
            }
        }

        return Resource.Error(response.message())
    }


}