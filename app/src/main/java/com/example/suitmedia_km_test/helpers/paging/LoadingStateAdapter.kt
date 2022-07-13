package com.example.suitmedia_km_test.helpers.paging

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.paging.LoadStateAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.suitmedia_km_test.databinding.LayoutLoadingBinding

class LoadingStateAdapter(private val retry: () -> Unit) :LoadStateAdapter<LoadingStateAdapter.ViewHolder>(){

    class ViewHolder(private val binding : LayoutLoadingBinding,retry: () -> Unit): RecyclerView.ViewHolder(binding.root) {
        init {
            binding.retryButton.setOnClickListener { retry.invoke() }
        }
        fun bind(state : LoadState){
            if (state is LoadState.Error){
                binding.errorMsg.text = state.error.toString()
            }
            binding.progressBar.isVisible = state is LoadState.Loading
            binding.retryButton.isVisible = state is LoadState.Error
            binding.errorMsg.isVisible = state is LoadState.Error
        }
    }

    override fun onBindViewHolder(holder: ViewHolder, loadState: LoadState) {
        holder.bind(loadState)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup, loadState: LoadState): ViewHolder {
        val binding = LayoutLoadingBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding,retry)
    }

}