package com.example.suitmedia_km_test.presentation.third

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmedia_km_test.R
import com.example.suitmedia_km_test.data.local.entity.user.NameTaken
import com.example.suitmedia_km_test.databinding.ActivityThirdScreenBinding
import com.example.suitmedia_km_test.helpers.paging.LoadingStateAdapter
import com.example.suitmedia_km_test.presentation.second.SecondScreenActivity
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

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                window.statusBarColor = getColor(R.color.white)
                window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            }
        }

        showUserList()

        binding.swipeRefreshThrid.setOnRefreshListener {
            showUserList()
        }

        binding.btnbackThird.setOnClickListener {
            startActivity(Intent(this@ThirdScreenActivity,SecondScreenActivity::class.java))
        }
    }

    private fun showUserList(){
        viewModel.getUsersList().observe(this){response->
            val recyclerView = binding.recyclerViewThird
            val adapter = UsersRecyclerViewAdapter()
            recyclerView.adapter = adapter
            recyclerView.layoutManager = LinearLayoutManager(this)
            recyclerView.adapter = adapter.withLoadStateFooter(
                LoadingStateAdapter{ adapter.retry()}
            )
            lifecycleScope.launch {
                adapter.submitData(response)
            }
            adapter.onItemClickDetail(object : UsersRecyclerViewAdapter.OnDetailCallback{
                override fun onDetailCallBack(name: String) {
                    viewModel.saveNameTaken(NameTaken(0,name))
                    startActivity(Intent(this@ThirdScreenActivity,SecondScreenActivity::class.java))
                }
            })

            if (adapter.itemCount == 0){
                binding.tvthirdEmptystate.visibility = View.GONE
            }
        }
    }

}