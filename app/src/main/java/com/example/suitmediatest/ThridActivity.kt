package com.example.suitmediatest

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.suitmediatest.data.adapter.LoadingStateAdapter
import com.example.suitmediatest.data.adapter.UserAdapter
import com.example.suitmediatest.databinding.ActivityThridBinding

class ThridActivity : AppCompatActivity() {

    private lateinit var binding: ActivityThridBinding

    private val viewModel by viewModels<MainViewModel> {
        ViewModelFactory.getInstance(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityThridBinding.inflate(layoutInflater)
        setContentView(binding.root)

        getData()
        binding.swipeRefreshLayout.setOnRefreshListener {
            refreshGetData()
        }
    }

    private fun getData() {
        val adapter = UserAdapter{ user ->
            val name = "${user.firstName} ${user.lastName}"
            val resultIntent = Intent()
            resultIntent.putExtra("extra_users", name)
            setResult(RESULT_OK, resultIntent)
            finish()
        }
        adapter.addLoadStateListener { loadState ->
            val isEmptyList = loadState.refresh is LoadState.NotLoading &&
                    adapter.itemCount == 0


            binding.emptyView.isVisible = isEmptyList

            binding.progressBar.isVisible = loadState.source.refresh is LoadState.Loading

            val errorState = loadState.source.append as? LoadState.Error
                ?: loadState.source.prepend as? LoadState.Error
                ?: loadState.refresh as? LoadState.Error

            errorState?.let {
                Toast.makeText(this, "Error: ${it.error}", Toast.LENGTH_SHORT).show()
            }
        }
        binding.apply {
            rvUsers.layoutManager = LinearLayoutManager(this@ThridActivity)
            rvUsers.adapter = adapter.withLoadStateFooter(
                footer = LoadingStateAdapter {
                    adapter.retry()
                }
            )
            viewModel.userData.observe(this@ThridActivity) {
                adapter.submitData(this@ThridActivity.lifecycle, it)
                swipeRefreshLayout.isRefreshing = false
            }
            btnBack.setOnClickListener {
                finish()
            }
        }
    }

    private fun refreshGetData() {
        binding.swipeRefreshLayout.isRefreshing = true
        getData()
    }


}