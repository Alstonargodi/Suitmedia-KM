package com.example.suitmedia_km_test.presentation.third.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.suitmedia_km_test.data.remote.Data
import com.example.suitmedia_km_test.data.remote.UsersResponse
import com.example.suitmedia_km_test.databinding.ItemcardUsersBinding

class UsersRecyclerViewAdapter(): PagingDataAdapter<Data,UsersRecyclerViewAdapter.ViewHolder>(DIFF_CALLBACK) {
    class ViewHolder(private var binding: ItemcardUsersBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(item : Data){
            binding.itemcardName.text = item.firstName + item.lastName
            binding.itemcardEmail.text = item.email

            Glide.with(binding.root)
                .load(item.avatar)
                .circleCrop()
                .into(binding.itemcardPic)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(ItemcardUsersBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        if (item != null){
            holder.bind(item)
        }
    }

    companion object {
        val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Data>() {
            override fun areItemsTheSame(oldItem: Data, newItem: Data): Boolean {
                return newItem.id == oldItem.id
            }

            override fun areContentsTheSame(oldItem: Data, newItem: Data): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }


}