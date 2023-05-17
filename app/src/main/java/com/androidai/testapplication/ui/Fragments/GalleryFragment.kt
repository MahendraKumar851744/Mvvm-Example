package com.androidai.testapplication.ui.Fragments

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidai.testapplication.Activities.MainActivity
import com.androidai.testapplication.Adapters.GalleryAdapter
import com.androidai.testapplication.R
import com.androidai.testapplication.ViewModels.ProductsViewModel
import com.androiddevs.mvvmnewsapp.util.Resource

class GalleryFragment:Fragment(R.layout.gallery_fragment) {
    lateinit var viewModel: ProductsViewModel
    lateinit var galleryAdapter: GalleryAdapter

    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progress_circular);
        recyclerView = view.findViewById(R.id.recycler_view);

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
        viewModel.gallery.observe(viewLifecycleOwner, Observer {response->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let{galleryResponse ->
                        galleryAdapter.differ.submitList(galleryResponse)
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let {message->
                        Log.e("GAALLERY",message);
                    }
                }
            }
        })
        Log.d("GAALLERY","galleryResponse.toString()");


    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        galleryAdapter = GalleryAdapter(requireActivity().supportFragmentManager)
        recyclerView.apply {
            adapter = galleryAdapter
            layoutManager = GridLayoutManager(activity,3)

        }
    }
}