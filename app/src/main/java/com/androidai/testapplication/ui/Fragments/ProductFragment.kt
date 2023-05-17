package com.androidai.testapplication.ui.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidai.testapplication.Activities.MainActivity
import com.androidai.testapplication.Adapters.productsAdapter
import com.androidai.testapplication.Models.Product
import com.androidai.testapplication.R
import com.androidai.testapplication.ViewModels.ProductsViewModel
import com.androiddevs.mvvmnewsapp.util.Resource
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


class ProductFragment : Fragment(R.layout.fragment_product),productsAdapter.OnItemClickListener {
    lateinit var viewModel: ProductsViewModel
    lateinit var productsAdapter: productsAdapter

    lateinit var recyclerView: RecyclerView
    lateinit var progressBar: ProgressBar


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        progressBar = view.findViewById(R.id.progress_circular);
        recyclerView = view.findViewById(R.id.recycler_view);

        viewModel = (activity as MainActivity).viewModel
        setupRecyclerView()
        productsAdapter.setOnItemClickListener(this)
        viewModel.products.observe(viewLifecycleOwner, Observer {response->
            when(response){
                is Resource.Success ->{
                    hideProgressBar()
                    response.data?.let{productResponse ->
                        productsAdapter.differ.submitList(productResponse.products)
                    }
                }
                is Resource.Loading ->{
                    showProgressBar()
                }
                is Resource.Error ->{
                    hideProgressBar()
                    response.message?.let {message->
                        Log.e("APPLICATION",message);
                    }
                }
            }
        })

    }

    override fun onItemClick(product: Product) {
        Log.d("dsvdv",product.toString());
        val pre = ProductDetailFrament()
        var bundle = Bundle().apply {
            putSerializable("product",product);
        }
        pre.arguments = bundle
        replaceFragment(pre)
    }

    private fun hideProgressBar() {
        progressBar.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        progressBar.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        productsAdapter = productsAdapter(requireActivity().supportFragmentManager)
        recyclerView.apply {
            adapter = productsAdapter
            layoutManager = LinearLayoutManager(activity)

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