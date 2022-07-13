package com.example.suitmedia_km_test.presentation.second

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import com.example.suitmedia_km_test.R
import com.example.suitmedia_km_test.databinding.ActivitySecondScreenBinding
import com.example.suitmedia_km_test.presentation.first.MainActivity
import com.example.suitmedia_km_test.presentation.third.ThirdScreenActivity
import com.example.suitmedia_km_test.presentation.viewmodel.ViewModelFactory

class SecondScreenActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondScreenBinding
    private val viewModel : SecondScreenViewModel by viewModels{  ViewModelFactory.getInstance(this) }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.statusBarColor = getColor(R.color.white)
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }

        showCurrentUser()
        showUserTaken()


        binding.btnChooseUser.setOnClickListener {
            startActivity(Intent(this,ThirdScreenActivity::class.java))
        }

        binding.btnbackSecond.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
            this.finishAffinity()
        }
    }

    private fun showCurrentUser(){
        viewModel.readUserName().observe(this){ respon ->
            if (respon != null){
                binding.tvSecondUsername.text = respon.name
            }
        }
    }

    private fun showUserTaken(){
        viewModel.readNameTaken().observe(this){ data ->
            if (data != null) {
                binding.tvSecondSelecteduser.text = data.name
            }
        }
    }
}