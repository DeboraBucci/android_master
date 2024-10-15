package com.example.androidmaster.superheroapp

import android.os.Bundle
import android.view.LayoutInflater
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmaster.R
import com.example.androidmaster.databinding.ActivityDetailSuperHeroBinding

class DetailSuperHeroActivity : AppCompatActivity() {

    private lateinit var binding : ActivityDetailSuperHeroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityDetailSuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}