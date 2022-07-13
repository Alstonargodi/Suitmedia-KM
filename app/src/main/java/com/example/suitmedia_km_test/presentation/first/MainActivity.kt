package com.example.suitmedia_km_test.presentation.first

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import com.example.suitmedia_km_test.data.local.entity.user.User
import com.example.suitmedia_km_test.databinding.ActivityMainBinding
import com.example.suitmedia_km_test.helpers.utils.Utils
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
        val name = binding.etfirstInsertName.text.toString()
        val word = binding.etfirstInsertWord.text.toString()
        val result = Utils.palindromeChecker(word)
        if (word.isNotEmpty()){
            if (name.isEmpty()){
                showDialog("isPalindrome",result.toString(),true)
            }else{
                showDialog("isPalindrome",result.toString(),false)
            }
        }else{
            showDialog("please fill out the palindromebox first","",true)
        }
    }

    private fun saveUserName(){
        val name = binding.etfirstInsertName.text.toString()
        if (name.isNotEmpty()){
            viewModel.insertUserName(User(0,name))
            goToSecondScreen()
        }else{
            showDialog("please fill namebox first","",true)
        }
    }

    private fun showDialog(title : String,subTitle : String,empty : Boolean){
        AlertDialog.Builder(this).apply {
            setTitle(title)
            setMessage(subTitle)
            apply {
                setPositiveButton("yes") { _, _ ->
                    if (!empty){
                        goToSecondScreen()
                        saveUserName()
                    }
                }
                setNegativeButton("no") { dialog, _ ->
                    dialog.dismiss()
                }
            }
            create()
            show()
        }
    }

    private fun goToSecondScreen(){
        startActivity(Intent(this@MainActivity,SecondScreenActivity::class.java))
        this.finishAffinity()
        this.finish()
    }

}