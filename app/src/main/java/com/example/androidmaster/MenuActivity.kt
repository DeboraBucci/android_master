package com.example.androidmaster

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.androidmaster.firstapp.FirstAppActivity
import com.example.androidmaster.imcapp.ImcCalculatorActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // BUTTONS
        val btnSaludapp = findViewById<Button>(R.id.btnSaludapp)
        val btnImcApp = findViewById<Button>(R.id.btnIMCApp)

        // LISTENERS
        btnSaludapp.setOnClickListener {
            navigateToSaludapp()
        }

        btnImcApp.setOnClickListener {
            navigateToIMCApp()
        }
    }

    private fun navigateToSaludapp () {
        val intent = Intent(this, FirstAppActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToIMCApp () {
        val intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }
}