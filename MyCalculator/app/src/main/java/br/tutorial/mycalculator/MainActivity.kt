package br.tutorial.mycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    var tvInput: TextView? = null
    var isLastNumeric = true
    var operator: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvInput = findViewById(R.id.tvInput)
    }

    fun onDigit(view: View) {
        val textButton = (view as? Button)?.text
        tvInput?.append(textButton)
        isLastNumeric = true
    }

    fun onDecimalPoint(view: View) {
        if(operator != null){
            var splitted = tvInput?.text?.split(operator  ?: "")
            val two = splitted?.get(1)
            if ((two?.contains(".") == false) && isLastNumeric) {
                tvInput?.append(".")
                isLastNumeric = false
            }
        }else {
            if ((tvInput?.text?.contains(".") == false) && isLastNumeric) {
                tvInput?.append(".")
                isLastNumeric = false
            }
        }
    }

    fun onClear(view: View) {
        tvInput?.text = ""
        isLastNumeric = true
        operator = null
    }

    fun onOperator(view: View) {
        val textButton = (view as? Button)?.text
        if (shouldAddOperator(tvInput?.text.toString()) && isLastNumeric) {
            tvInput?.append(textButton)
            operator = textButton.toString()
            isLastNumeric = false
        }
    }

    fun onEqual(view: View){
        if(isLastNumeric){
            var tvValue = tvInput?.text.toString()
            var prefix = ""
            try{
                if(tvValue.startsWith("-")){
                    prefix = "-"
                    tvValue = tvValue.substring(1)
                }
                if(tvValue.contains("-")){
                    val splitValues = tvValue.split("-")
                    var one = "$prefix${splitValues[0]}"
                    var two = splitValues[1]

                    tvInput?.text = (one.toDouble() - two.toDouble()).toString()
                } else if(tvValue.contains("+")){
                    val splitValues = tvValue.split("+")
                    var one = "$prefix${splitValues[0]}"
                    var two = splitValues[1]

                    tvInput?.text = (one.toDouble() + two.toDouble()).toString()
                }else if(tvValue.contains("/")){
                    val splitValues = tvValue.split("/")
                    var one = "$prefix${splitValues[0]}"
                    var two = splitValues[1]

                    tvInput?.text = (one.toDouble() / two.toDouble()).toString()
                }else if(tvValue.contains("*")){
                    val splitValues = tvValue.split("*")
                    var one = "$prefix${splitValues[0]}"
                    var two = splitValues[1]

                    tvInput?.text = (one.toDouble() * two.toDouble()).toString()
                }
            } catch (e: ArithmeticException){
                e.printStackTrace()
            }
        }
    }

    private fun shouldAddOperator(value: String): Boolean {
        if (value.startsWith("-")) {
            return true
        }
        if (value.contains("+") || value.contains("-") || value.contains("/") || value.contains("-")) {
            return false
        }
        return true
    }
}