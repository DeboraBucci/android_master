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
import com.example.androidmaster.superheroapp.SuperHeroListActivity
import com.example.androidmaster.todoapp.TodoActivity

class MenuActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

        // BUTTONS
        val btnSaludapp = findViewById<Button>(R.id.btnSaludapp)
        val btnImcApp = findViewById<Button>(R.id.btnIMCApp)
        val btnTodoApp = findViewById<Button>(R.id.btnTODOApp)
        val btnSuperHeroApp = findViewById<Button>(R.id.btnSuperHeroApp)

        // LISTENERS
        btnSaludapp.setOnClickListener {
            navigateToSaludapp()
        }

        btnImcApp.setOnClickListener {
            navigateToIMCApp()
        }

        btnTodoApp.setOnClickListener {
            navigateToTODOApp()
        }

        btnSuperHeroApp.setOnClickListener {
            navigateToSuperHeroApp()
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

    private fun navigateToTODOApp () {
        val intent = Intent(this, TodoActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToSuperHeroApp () {
        val intent = Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)
    }
}