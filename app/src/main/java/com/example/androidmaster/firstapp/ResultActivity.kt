package com.example.androidmaster.firstapp

import android.os.Bundle
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmaster.R

class ResultActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val name: String = intent.extras?.getString("EXTRA_NAME").orEmpty()

        val tvResult = findViewById<TextView>(R.id.tvResult)

        tvResult.text = "Bienvenido, $name!"
    }
}