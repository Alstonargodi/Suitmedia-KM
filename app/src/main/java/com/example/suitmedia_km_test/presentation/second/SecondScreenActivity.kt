package com.example.suitmedia_km_test.presentation.second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import com.example.suitmedia_km_test.R
import com.example.suitmedia_km_test.databinding.ActivitySecondScreenBinding
import com.example.suitmedia_km_test.presentation.third.ThirdScreenActivity
import com.example.suitmedia_km_test.presentation.viewmodel.ViewModelFactory

class SecondScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding
    private val viewModel : SecondScreenViewModel by viewModels{  ViewModelFactory.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnChooseUser.setOnClickListener {
            startActivity(Intent(this,ThirdScreenActivity::class.java))
        }

        showUser()
    }

    private fun showUser(){
        viewModel.readUserName().observe(this){ respon ->
            binding.tvSecondUsername.text = respon.name
        }
    }
}