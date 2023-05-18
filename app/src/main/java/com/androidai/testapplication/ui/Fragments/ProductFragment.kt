package com.androidai.testapplication.ui.Fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.androidai.testapplication.ui.Activities.MainActivity
import com.androidai.testapplication.Adapters.productsAdapter
import com.androidai.testapplication.Models.Product
import com.androidai.testapplication.R
import com.androidai.testapplication.ViewModels.ProductsViewModel
import com.androidai.testapplication.databinding.FragmentProductBinding
import com.androidai.testapplication.util.Defaults.Companion.replaceFragment
import com.androiddevs.mvvmnewsapp.util.Resource
import com.bumptech.glide.RequestManager
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

class ProductFragment : Fragment(),productsAdapter.OnItemClickListener {
    lateinit var viewModel: ProductsViewModel
    lateinit var productsAdapter: productsAdapter

    private var _binding: FragmentProductBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentProductBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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
        val pre = ProductDetailFrament()
        var bundle = Bundle().apply {
            putSerializable("product",product);
        }
        pre.arguments = bundle
        val fragmentManager = requireActivity().supportFragmentManager
        replaceFragment(fragmentManager,pre)
    }

    private fun hideProgressBar() {
        binding.progressCircular.visibility = View.INVISIBLE
    }

    private fun showProgressBar() {
        binding.progressCircular.visibility = View.VISIBLE
    }

    private fun setupRecyclerView(){
        productsAdapter = productsAdapter(requireActivity().supportFragmentManager)
        binding.recyclerView.apply {
            adapter = productsAdapter
            layoutManager = LinearLayoutManager(activity)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
    fun replaceFragment(fragmentManager: FragmentManager, fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.flFragment, fragment)
        fragmentTransaction.addToBackStack(null)
        fragmentTransaction.commit()
    }

}