package com.piko29.bikecalc

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity


/**
 * This application is calculating
 * approximate frame size, according to your inseam,
 * and type of bicycle you want to buy.
 * Enjoy!!
 */

class MainActivity : ComponentActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
//defining buttons for further actions
        val roadCalculation = findViewById<ImageButton>(R.id.roadBikeButton)
        val mountainCalculation = findViewById<ImageButton>(R.id.mountainBikeButton)
        val cityCalculation = findViewById<ImageButton>(R.id.cityBikeButton)
        val predictInseam = findViewById<Button>(R.id.predictButton)
//clickListener handling
        predictInseam.setOnClickListener {
        predictInseamFunction()
        }
        roadCalculation.setOnClickListener {
        displayResult(0.66f)
        }
        mountainCalculation.setOnClickListener{
        displayResult(0.6f)
        }
        cityCalculation.setOnClickListener{
        displayResult(0.63f)
        }
    }
//displaying results in bottom fields
        private fun displayResult(factor: Float){
        val heightText = findViewById<EditText>(R.id.editTextNumberHeight)
        val inseamText = findViewById<EditText>(R.id.editTextNumberInseam)
        val height = heightText.text.toString()
        val inseam = inseamText.text.toString()
        if (validateInputCalculation(height,inseam)) {

            val resultCm = inseam.toFloat() * factor
            val resultCm2Digits = String.format("%.2f", resultCm)

            val resultIn = resultCm / 2.54
            val resultIn2Digits = String.format("%.2f", resultIn)

            val displayResultCm = findViewById<TextView>(R.id.textViewResultCm)
            displayResultCm.text = "$resultCm2Digits cm"

            val displayResultIn = findViewById<TextView>(R.id.textViewResultIn)
            displayResultIn.text = "$resultIn2Digits in"
        }
    }
//predicting inseam
    private fun predictInseamFunction() {
        val heightText = findViewById<EditText>(R.id.editTextNumberHeight)
        val height = heightText.text.toString()
        if (validateInputPrediction(height)) {
            val predictedInseam = findViewById<TextView>(R.id.predictedValue)
            val heightToInseam = ((height.toFloat() * 0.465).toInt()).toString()
            predictedInseam.text = "Predicted inseam is $heightToInseam cm"
        }
    }

//checking if there are enough values
    private fun validateInputCalculation(height:String?,inseam:String?):Boolean{
        return when{
            height.isNullOrEmpty() -> {
                Toast.makeText(this,"height is empty",Toast.LENGTH_LONG).show()
                return false
            }
            inseam.isNullOrEmpty() -> {
                Toast.makeText(this,"inseam is empty",Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }
//checking if there is height to predict inseam
    private fun validateInputPrediction(height: String?):Boolean {
        return when {
            height.isNullOrEmpty() -> {
                Toast.makeText(this, "height is empty", Toast.LENGTH_LONG).show()
                return false
            }
            else -> {
                return true
            }
        }
    }
}