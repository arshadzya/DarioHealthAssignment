package com.arshad.dariohealthassignment.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.arshad.dariohealthassignment.R
import com.arshad.dariohealthassignment.databinding.ActivityMainBinding
import com.arshad.dariohealthassignment.databinding.ActivityOpeningBinding

class OpeningActivity : AppCompatActivity() {

    private lateinit var binding: ActivityOpeningBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_opening)

        binding = ActivityOpeningBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imageView4.setOnClickListener {

            startActivity(Intent(this@OpeningActivity, MainActivity::class.java))
        }

        binding.textView5.setOnClickListener {
            startActivity(Intent(this@OpeningActivity, MainActivity::class.java))
        }

    }
}