package com.example.fastre.ui.setting

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.fastre.databinding.ActivityAboutUsBinding

class AboutUsActivity : AppCompatActivity() {
    private var _binding: ActivityAboutUsBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityAboutUsBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}