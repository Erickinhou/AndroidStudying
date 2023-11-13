package br.tutorial.myquiz

import android.content.Intent
import android.graphics.Typeface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.core.content.ContextCompat

class QuizQuestionActivity() : AppCompatActivity(), View.OnClickListener {
    private var currentPosition = 0
    private var countCorrectAnswers = 0
    private var selectedQuestion: Int? = null
    private var questionsList = Constants.getQuestions()
    private var progressBar: ProgressBar? = null
    private var tvProgress: TextView? = null
    private var tvQuestion: TextView? = null
    private var tvImage: ImageView? = null
    private var tvOptionOne: TextView? = null
    private var tvOptionTwo: TextView? = null
    private var tvOptionThree: TextView? = null
    private var tvOptionFour: TextView? = null
    private var btnSubmit: Button? = null
    private var userName: String? = null
    private var isAnswered: Boolean = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz_question)

        progressBar = findViewById(R.id.progress_bar)
        tvProgress = findViewById(R.id.tv_progress_bar)
        tvQuestion = findViewById(R.id.tv_question)
        tvImage = findViewById(R.id.tv_image)
        btnSubmit = findViewById(R.id.btn_submit)
        tvOptionOne = findViewById(R.id.tv_option_one)
        tvOptionTwo = findViewById(R.id.tv_option_two)
        tvOptionThree = findViewById(R.id.tv_option_three)
        tvOptionFour = findViewById(R.id.tv_option_four)

        userName = intent.getStringExtra(Constants.USER_NAME)

        setQuestions()
        btnSubmit?.setOnClickListener(this)
        tvOptionOne?.setOnClickListener(this)
        tvOptionTwo?.setOnClickListener(this)
        tvOptionThree?.setOnClickListener(this)
        tvOptionFour?.setOnClickListener(this)
    }

    private fun setQuestions() {
        val question: Question = questionsList[currentPosition]
        defaultOptionsView()
        progressBar?.progress = currentPosition + 1
        tvProgress?.text = "${currentPosition + 1} / ${progressBar?.max}"
        tvImage?.setImageResource(question.image)
        tvOptionOne?.text = question.optionOne
        tvOptionTwo?.text = question.optionTwo
        tvOptionThree?.text = question.optionThree
        tvOptionFour?.text = question.optionFour

        if (currentPosition == questionsList.size) {
            btnSubmit?.text = "Finish"
        } else {
            btnSubmit?.text = "Submit"
        }

    }

    private fun defaultOptionsView() {
        val options = ArrayList<TextView>()

        tvOptionOne?.let {
            options.add(0, it)
        }
        tvOptionTwo?.let {
            options.add(1, it)
        }
        tvOptionThree?.let {
            options.add(2, it)
        }
        tvOptionFour?.let {
            options.add(3, it)
        }

        for (option in options) {
            option.setTextColor(ContextCompat.getColor(this, R.color.lightGray))
            option.typeface = Typeface.DEFAULT
            option.setBackgroundResource(R.drawable.default_options_border_bg)
        }
    }

    private fun selectOptionView(option: TextView, questionIndex: Int) {
        selectedQuestion = questionIndex
        option.setTextColor(ContextCompat.getColor(this, R.color.gray))
        option.setTypeface(null, Typeface.BOLD)
        option.setBackgroundResource(R.drawable.selected_options_border_bg)
    }

    private fun checkAnswer() {
        if (selectedQuestion == null || isAnswered === true) {
            goToNextQuestion()
        } else {
            if (selectedQuestion != questionsList[currentPosition].correctAnswer) {
                selectedQuestion?.let {
                    answerView(it, R.drawable.wrong_options_border_bg)
                }
            } else {
                countCorrectAnswers++
            }
            answerView(
                questionsList[currentPosition].correctAnswer,
                R.drawable.correct_options_border_bg
            )
            if (currentPosition == questionsList.size) {
                btnSubmit?.text = "Finish"
            } else {
                btnSubmit?.text = "Next"
            }
            selectedQuestion = null
            isAnswered = true
        }
    }

    private fun goToNextQuestion() {
        currentPosition++
        when {
            currentPosition < questionsList.size -> {
                setQuestions()
                isAnswered = false
            }

            else -> {
                val intent = Intent(this, ResultsUserActivity::class.java)
                intent.putExtra(Constants.USER_NAME, userName)
                intent.putExtra(Constants.TOTAL_QUESTIONS, questionsList.size)
                intent.putExtra(Constants.CORRECT_ANSWERS, countCorrectAnswers)
                startActivity(intent)
            }
        }
    }

    private fun answerView(answer: Int, drawable: Int) {
        when (answer) {
            1 -> {
                tvOptionOne?.background = ContextCompat.getDrawable(this, drawable)
            }

            2 -> {
                tvOptionTwo?.background = ContextCompat.getDrawable(this, drawable)
            }

            3 -> {
                tvOptionThree?.background = ContextCompat.getDrawable(this, drawable)
            }

            4 -> {
                tvOptionFour?.background = ContextCompat.getDrawable(this, drawable)
            }
        }
    }


    override fun onClick(view: View?) {
        when (view?.id) {
            R.id.tv_option_one -> {
                defaultOptionsView()
                tvOptionOne?.let { selectOptionView(it, 1) }
            }

            R.id.tv_option_two -> {
                defaultOptionsView()
                tvOptionTwo?.let { selectOptionView(it, 2) }
            }

            R.id.tv_option_three -> {
                defaultOptionsView()
                tvOptionThree?.let { selectOptionView(it, 3) }
            }

            R.id.tv_option_four -> {
                defaultOptionsView()
                tvOptionFour?.let { selectOptionView(it, 4) }
            }

            R.id.btn_submit -> {
                checkAnswer()
            }
        }
    }

}