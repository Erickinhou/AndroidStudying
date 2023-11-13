package br.tutorial.myquiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class ResultsUserActivity : AppCompatActivity() {
    private var userName: String? = null
    private var correctAnswers: Int? = null
    private var countTotalQuestions: Int? = null
    private var tvUsername: TextView? = null
    private var tvScore: TextView? = null
    private var btnResult: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_results_user)
        userName = intent.getStringExtra(Constants.USER_NAME)
        correctAnswers = intent.getIntExtra(Constants.CORRECT_ANSWERS, 0)
        countTotalQuestions = intent.getIntExtra(Constants.TOTAL_QUESTIONS, 0)

        tvUsername = findViewById(R.id.tv_user_name)
        tvScore = findViewById(R.id.tv_score)
        btnResult = findViewById(R.id.btn_result)

        tvScore?.text = "Your score $correctAnswers out of $countTotalQuestions"
        tvUsername?.text = "$userName"

        btnResult?.setOnClickListener(){
            startActivity(Intent(this, MainActivity::class.java))
        }
    }

}