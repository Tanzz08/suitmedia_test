package com.example.suitmediatest

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.suitmediatest.databinding.ActivityMainBinding
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private  lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            btnCheck.setOnClickListener {
                val sentence = edPalindrome.text.toString()

                if (sentence.isEmpty()) {
                    showResultDialog("Masukkan kalimat terlebih dahulu")
                    return@setOnClickListener
                }

                val isPalindrome = checkPalindrome(sentence)
                val resultMessage: String

                if (isPalindrome) {
                    resultMessage = "isPalindrome"
                } else {
                    resultMessage = "notPalindrome"
                }
                showResultDialog(resultMessage)
            }
            btnNext.setOnClickListener {
                val name = edName.text.toString()

                if (name.isEmpty()) {
                    showResultDialog("Masukkan nama terlebih dahulu")
                    return@setOnClickListener
                }
                secondScreenIntent()
            }
        }
    }

    private fun checkPalindrome(sentence: String): Boolean {
        val processedSentence = sentence.replace("\\s".toRegex(), "").lowercase()
        val reveredSentence = processedSentence.reversed()
        return processedSentence == reveredSentence
    }

    private fun showResultDialog( message: String) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton("OK") {dialog, _ ->
                dialog.dismiss()
            }
            .show()
    }

    private fun secondScreenIntent() {
        val intent = Intent(this, SecondActivity::class.java)
        intent.putExtra("extra_name", binding.edName.text.toString())
        startActivity(intent)
    }
}