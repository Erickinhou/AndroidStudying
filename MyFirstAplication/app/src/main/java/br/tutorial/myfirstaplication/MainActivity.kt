package br.tutorial.myfirstaplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        print("text with multiple things")
        val btnClickMe = findViewById<Button>(R.id.mybutton)
        val text = findViewById<TextView>(R.id.textView)
        var count = 0

        btnClickMe.setOnClickListener () {
            count++
            text.text = count.toString()
        }
    }
}