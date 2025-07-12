package com.example.suitmediatest

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suitmediatest.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        enableEdgeToEdge()
        setContentView(binding.root)

        val name = intent.getStringExtra(EXTRA_NAME)
        binding.textName.text = name
        binding.apply {
            btnBack.setOnClickListener {
                finish()
            }
            btnChooseUser.setOnClickListener {
                val intent = Intent(this@SecondActivity, ThridActivity::class.java)
                launcher.launch(intent)
            }
        }
    }

    private val launcher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
        if (result.resultCode == Activity.RESULT_OK) {
            val name = result.data?.getStringExtra(EXTRA_USERS)
            binding.textSelectedName.text = name
        }
    }

    companion object {
        const val EXTRA_NAME = "extra_name"
        const val EXTRA_USERS = "extra_users"
    }
}