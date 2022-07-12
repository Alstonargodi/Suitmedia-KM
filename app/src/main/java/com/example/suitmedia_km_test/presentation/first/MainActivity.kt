package com.example.suitmedia_km_test.presentation.first

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.suitmedia_km_test.data.local.entity.user.User
import com.example.suitmedia_km_test.databinding.ActivityMainBinding
import com.example.suitmedia_km_test.helpers.Utils
import com.example.suitmedia_km_test.presentation.second.SecondScreenActivity
import com.example.suitmedia_km_test.presentation.viewmodel.ViewModelFactory

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel : FirstScreenViewModel by viewModels{ ViewModelFactory.getInstance(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnfirstCheckPalindrome.setOnClickListener {
            checkPalindrome()
        }

        binding.btnfirstCheckNext.setOnClickListener {
            saveUserName()
        }
    }

    private fun checkPalindrome(){
        val word = binding.etfirstInsertWord.text.toString()
        val result = Utils.palindromeChecker(word)
        if (word.isNotEmpty()){
             if (result){
                showDialog("isPalindrome",result.toString())
             }else{
                 showDialog("isPalindrome",result.toString())
             }
        }else{
            showDialog(
                "please fill out the box first","continue ?"
            )
        }
    }

    private fun saveUserName(){
        val name = binding.etfirstInsertName.text.toString()
        if (name.isNotEmpty()){
            viewModel.insertUserName(User(0,name))
            startActivity(Intent(this@MainActivity,SecondScreenActivity::class.java))
        }else{
            showDialog("please fill name box","continue ?")
        }
    }

    private fun showDialog(title : String,subTitle : String){
        AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(subTitle)
            apply {
                setPositiveButton("yes") { _, _ ->
                    startActivity(Intent(this@MainActivity,SecondScreenActivity::class.java))
                }
                setNegativeButton("no") { dialog, _ ->
                    dialog.dismiss()
                }
            }
            create()
            show()
        }
    }
}