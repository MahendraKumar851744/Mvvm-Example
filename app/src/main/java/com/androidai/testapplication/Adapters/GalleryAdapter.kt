package com.androidai.testapplication.Adapters

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.androidai.testapplication.Models.Product
import com.androidai.testapplication.Models.galleryItem
import com.androidai.testapplication.R
import com.androidai.testapplication.ui.Fragments.ProductDetailFrament
import com.bumptech.glide.Glide

class GalleryAdapter(val fragmentManager: FragmentManager): RecyclerView.Adapter<GalleryAdapter.ProductHolder>() {

    private var onItemClickListener: GalleryAdapter.OnItemClickListener? = null

    inner class ProductHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val iv_image: ImageView = itemView.findViewById(R.id.iv_image)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductHolder {
        return ProductHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.gallery_item,parent,false)
        )
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    override fun onBindViewHolder(holder: ProductHolder, position: Int) {
        val item: galleryItem = differ.currentList.get(position)

        holder.itemView.apply{
            Glide.with(this).load(item.thumbnailUrl).into(holder.iv_image)
        }

        holder.itemView.setOnClickListener(View.OnClickListener {
            onItemClickListener?.onItemClick(item)
        })

    }

    private val differCallbac = object: DiffUtil.ItemCallback<galleryItem>(){
        override fun areItemsTheSame(oldItem: galleryItem, newItem: galleryItem): Boolean {
            return oldItem.id == newItem.id
        }
        override fun areContentsTheSame(oldItem: galleryItem, newItem: galleryItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this,differCallbac)

    interface OnItemClickListener {
        fun onItemClick(galleryItem: galleryItem)
    }

    fun setOnItemClickListener(listener: OnItemClickListener) {
        onItemClickListener = listener
    }


}