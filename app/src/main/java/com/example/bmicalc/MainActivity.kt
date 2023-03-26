package com.example.bmicalc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val weightText = findViewById<EditText>(R.id.etWeight)


        val heightText = findViewById<EditText>(R.id.etHeight)


        val calButton = findViewById<Button>(R.id.btnCalculate)

        calButton.setOnClickListener {
            val weight = weightText.text.toString()
            val height = heightText.text.toString()


            val bmi = weight.toFloat() / ((height.toFloat() / 100) * (height.toFloat() / 100))
            val bmi2Digits = String.format("%.2f", bmi).toFloat()

            displayResult(bmi2Digits)
        }

    }

    private fun validInput(weight:String?,height:String?):Boolean{
        return when{
            weight.isNullOrEmpty() -> {
                Toast.makeText(this,"wait!! Weight??",Toast.LENGTH_LONG).show()
                return false
            }
            height.isNullOrEmpty() -> {
                Toast.makeText(this,"hold!! Height??",Toast.LENGTH_LONG).show()
                return false
            }
            else ->{
                return true
            }

        }
    }






    private fun displayResult(bmi:Float){


        val resultIndex = findViewById<TextView>(R.id.tvIndex)
        val resultDesc = findViewById<TextView>(R.id.tvResult)
        val info = findViewById<TextView>(R.id.tvInfo)

        resultIndex.text = bmi.toString()
        info.text = "Normal BMI range-18.5 to 24.9"


        var color = 0
        var resultText = ""

        when{
            bmi < 18.50 ->{
                resultText="Underweight"
                color = R.color.under

            }
            bmi in 18.50..24.99->{
                resultText="Healthy :)"
                color = R.color.fit
            }
            bmi in 25.00..29.99->{
                resultText = "Overweight"
                color = R.color.over
            }
            bmi > 29.99 -> {
                resultText ="Obese"
                color = R.color.obs
            }



        }
        resultDesc.setTextColor(ContextCompat.getColor(this,color))
        resultDesc.text = resultText


    }



}