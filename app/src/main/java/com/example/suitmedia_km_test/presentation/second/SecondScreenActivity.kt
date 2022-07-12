package com.example.suitmedia_km_test.presentation.second

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.suitmedia_km_test.R
import com.example.suitmedia_km_test.databinding.ActivitySecondScreenBinding
import com.example.suitmedia_km_test.presentation.third.ThirdScreenActivity

class SecondScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnChooseUser.setOnClickListener {
            startActivity(Intent(this,ThirdScreenActivity::class.java))
        }
    }
}