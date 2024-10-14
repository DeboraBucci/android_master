package com.example.androidmaster.imcapp

import android.content.Intent
import android.icu.text.DecimalFormat
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.example.androidmaster.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider

class ImcCalculatorActivity : AppCompatActivity() {

    private lateinit var cvMale: CardView
    private lateinit var cvFemale: CardView

    private lateinit var tvHeight: TextView
    private lateinit var rgHeight: RangeSlider

    private lateinit var tvWeight: TextView
    private lateinit var btnIncreaseWeight: FloatingActionButton
    private lateinit var btnDecreaseWeight: FloatingActionButton

    private lateinit var tvAge: TextView
    private lateinit var btnIncreaseAge: FloatingActionButton
    private lateinit var btnDecreaseAge: FloatingActionButton

    private lateinit var btnCalculate: Button

    private var currWeight: Int = 60;
    private var currAge: Int = 20;
    private var currHeight: Int = 120;

    companion object {
        const val IMC_KEY = "IMC"
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator)

        initComponents()
        initListeners()
    }

    private fun initComponents() {
        cvMale = findViewById(R.id.cvMale)
        cvFemale = findViewById(R.id.cvFemale)

        tvHeight = findViewById(R.id.tvHeight)
        rgHeight = findViewById(R.id.rsHeight)

        tvWeight = findViewById(R.id.tvWeight)
        btnIncreaseWeight = findViewById(R.id.btnIncreaseWeight)
        btnDecreaseWeight = findViewById(R.id.btnDecreaseWeight)

        tvAge = findViewById(R.id.tvAge)
        btnIncreaseAge = findViewById(R.id.btnIncreaseAge)
        btnDecreaseAge = findViewById(R.id.btnDecreaseAge)

        btnCalculate = findViewById(R.id.btnCalculate)
    }

    private fun initListeners() {
        cvMale.setOnClickListener {
            setGenderColors(true)
        }

        cvFemale.setOnClickListener {
            setGenderColors(false)
        }

        rgHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#")
            val result = df.format(value)

            currHeight = result.toInt()
            tvHeight.text = "$result cm"
        }

        btnIncreaseWeight.setOnClickListener {
            currWeight++;
            setUpdatedWeight()
        }

        btnDecreaseWeight.setOnClickListener {
            currWeight--;
            setUpdatedWeight()
        }

        btnIncreaseAge.setOnClickListener {
            currAge++;
            setUpdatedAge()
        }

        btnDecreaseAge.setOnClickListener {
            currAge--;
            setUpdatedAge()
        }

        btnCalculate.setOnClickListener {
            navigateToResult(calculateIMC());
        }
    }

    private fun navigateToResult(imc: Double) {
        intent = Intent(this, ImcResultActivity::class.java)
        intent.putExtra(IMC_KEY, imc)
        startActivity(intent)
    }

    private fun setGenderColors(isMaleSelected: Boolean) {
        cvFemale.setCardBackgroundColor(getBgColor(!isMaleSelected))
        cvMale.setCardBackgroundColor(getBgColor(isMaleSelected))
    }

    private fun getBgColor(isSelected: Boolean): Int {

        val colorReference = if (isSelected) {
            R.color.background_component_selected
        } else {
            R.color.background_component
        }

        return ContextCompat.getColor(this, colorReference)
    }

    private fun setUpdatedWeight() {
        tvWeight.text = currWeight.toString()
    }

    private fun setUpdatedAge() {
        tvAge.text = currAge.toString()
    }

    private fun calculateIMC(): Double {
        val df = DecimalFormat("#.##")
        val imc = currWeight / ((currHeight.toDouble() / 100) * (currHeight.toDouble() / 100))

        return df.format(imc).toDouble()
    }
}