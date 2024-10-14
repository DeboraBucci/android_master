package com.example.androidmaster.imcapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.example.androidmaster.R
import com.example.androidmaster.imcapp.ImcCalculatorActivity.Companion.IMC_KEY

class ImcResultActivity : AppCompatActivity() {
    private lateinit var tvResult: TextView
    private lateinit var tvIMC: TextView
    private lateinit var tvDescription: TextView

    private lateinit var btnRecalculate: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_result)

        initComponents()

        val imc = intent.extras?.getDouble(IMC_KEY) ?: -1.0
        initUI(imc)

        eventListeners()
    }

    private fun initComponents() {
        tvResult = findViewById(R.id.tvResult)
        tvIMC = findViewById(R.id.tvIMC)
        tvDescription = findViewById(R.id.tvDescription)

        btnRecalculate = findViewById(R.id.btnRecalculate)
    }

    private fun initUI (imc: Double) {
        tvIMC.text = imc.toString()

        when (imc) {
            // UNDERWEIGHT
            in 0.00 .. 18.50 -> {
                tvResult.text = getString(R.string.underweight)
                tvDescription.text = getString(R.string.description_underweight)

                tvResult.setTextColor(ContextCompat.getColor(this, R.color.underweight))
            }

            // NORMAL WEIGHT
            in 18.51 .. 24.99 -> {
                tvResult.text = getString(R.string.normal_weight)
                tvDescription.text = getString(R.string.description_normal_weight)

                tvResult.setTextColor(ContextCompat.getColor(this, R.color.normal_weight))
            }

            // OVERWEIGHT
            in 25.00 .. 29.99 -> {
                tvResult.text = getString(R.string.overweight)
                tvDescription.text = getString(R.string.description_overweight)

                tvResult.setTextColor(ContextCompat.getColor(this, R.color.overweight))
            }

            // OBESE
            in 30.00 .. 999.99 -> {
                tvResult.text = getString(R.string.obesity)
                tvDescription.text = getString(R.string.description_obese)

                tvResult.setTextColor(ContextCompat.getColor(this, R.color.obese))
            }

            // ERROR
            else -> {
                tvResult.text = getString(R.string.error)
                tvDescription.text = ""
            }
        }
    }

    private fun eventListeners () {
        btnRecalculate.setOnClickListener {
            navigateToCalculator()
        }
    }

    private fun navigateToCalculator () {
        intent = Intent(this, ImcCalculatorActivity::class.java)
        startActivity(intent)
    }
}