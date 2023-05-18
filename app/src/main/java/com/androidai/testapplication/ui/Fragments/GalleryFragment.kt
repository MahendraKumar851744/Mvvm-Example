package com.androidai.testapplication.ui.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidai.testapplication.ui.Activities.MainActivity
import com.androidai.testapplication.Adapters.GalleryAdapter
import com.androidai.testapplication.Models.Product
import com.androidai.testapplication.Models.galleryItem
import com.androidai.testapplication.R
import com.androidai.testapplication.ViewModels.ProductsViewModel
import com.androidai.testapplication.databinding.FragGalleryDetailBinding
import com.androidai.testapplication.databinding.GalleryFragmentBinding
import com.androiddevs.mvvmnewsapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint

class GalleryFragment:Fragment(R.layout.gallery_fragment),GalleryAdapter.OnItemClickListener {
    lateinit var viewModel: ProductsViewModel
    lateinit var galleryAdapter: GalleryAdapter
    private var _binding: GalleryFragmentBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = GalleryFragmentBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
        galleryAdapter.setOnItemClickListener(this)
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
                    }
                }
            }
        })


    }
    override fun onItemClick(galleryItem: galleryItem) {
        val pre = GalleryDetailFrament()
        var bundle = Bundle().apply {
            putSerializable("gallery_product",galleryItem);
        }
        pre.arguments = bundle
        replaceFragment(pre)
    }
    private fun hideProgressBar() {
        binding.progressCircular.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        galleryAdapter = GalleryAdapter(requireActivity().supportFragmentManager)
        binding.recyclerView.apply {
            adapter = galleryAdapter
            layoutManager = GridLayoutManager(activity,3)

        }
    }
    private fun replaceFragment(fragment: Fragment) {
        val fragmentManager = requireActivity().supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.flFragment, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }
}