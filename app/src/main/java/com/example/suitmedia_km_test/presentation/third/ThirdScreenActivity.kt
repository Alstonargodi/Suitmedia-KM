package com.example.suitmedia_km_test.presentation.third

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmedia_km_test.R
import com.example.suitmedia_km_test.databinding.ActivityThirdScreenBinding
import com.example.suitmedia_km_test.presentation.third.adapter.UsersRecyclerViewAdapter
import com.example.suitmedia_km_test.presentation.viewmodel.ViewModelFactory
import kotlinx.coroutines.launch

class ThirdScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivityThirdScreenBinding
    private val viewModel : ThirdScreenViewModel by viewModels{
        ViewModelFactory.getInstance(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityThirdScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showUserList()
    }


    private fun showUserList(){
        viewModel.getUsersList().observe(this){response->
            val adapter = UsersRecyclerViewAdapter()
            binding.recyclerViewThird.adapter = adapter
            binding.recyclerViewThird.layoutManager = LinearLayoutManager(this)
            lifecycleScope.launch {
                adapter.submitData(response)
            }
        }
    }
}