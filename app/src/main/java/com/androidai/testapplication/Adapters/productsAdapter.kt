package com.androidai.testapplication.Adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androidai.testapplication.Models.Product
import com.androidai.testapplication.R
import com.bumptech.glide.Glide

class productsAdapter(val fragmentManager: FragmentManager): RecyclerView.Adapter<productsAdapter.ProductHolder>() {

    private var onItemClickListener: OnItemClickListener? = null
    inner class ProductHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val iv_image: ImageView = itemView.findViewById(R.id.iv_image)
        val tvTitle: TextView = itemView.findViewById(R.id.tv_title)
        val tvdesc: TextView = itemView.findViewById(R.id.tv_desc)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val item: Product = differ.currentList.get(position)

        holder.itemView.apply{
            Glide.with(this).load(item.images.get(0)).into(holder.iv_image)
            holder.tvTitle.text = item.title
            holder.tvdesc.text = item.description
        }

        holder.itemView.setOnClickListener(View.OnClickListener {
            onItemClickListener?.onItemClick(item)
        })

    }


    interface OnItemClickListener {
        fun onItemClick(product: Product)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }

    private val differCallbac = object: DiffUtil.ItemCallback<Product>(){
        override fun areItemsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: Product, newItem: Product): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallbac)

    private fun replaceFragment(fragment: Fragment) {
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.flFragment, fragment)
        fragmentTransaction.addToBackStack(null)

        fragmentTransaction.commit()
    }
}