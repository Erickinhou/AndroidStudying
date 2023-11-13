package br.tutorial.myquiz

object Constants {

    const val USER_NAME = "user_name"
    const val TOTAL_QUESTIONS = "total_questions"
    const val CORRECT_ANSWERS = "correct_answers"

    fun getQuestions(): ArrayList<Question> {
        val questionList = ArrayList<Question>()

        questionList.add(
            Question(
                1,
                "What country does this flag belongs to?",
                R.drawable.ic_flag_of_argentina,
                "Argentina",
                "Australia",
                "Brazil",
                "Armenia",
                1,
            )
        )

        questionList.add(Question(2,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_brazil,
            "Greece",
            "Australia",
            "Brazil",
            "Armenia",
            3
        ))

        questionList.add(Question(3,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_belgium,
            "Greece",
            "Germany",
            "Belgium",
            "Colombia",
            3
        ))
        questionList.add(Question(4,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_denmark,
            "Norway",
            "Denmark",
            "Ireland",
            "Monaco",
            2
        ))
        questionList.add(Question(5,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_fiji,
            "Australia",
            "New Zeeland",
            "Fiji islands",
            "Trinidad and Tobago islands",
            3
        ))
        questionList.add(Question(6,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_kuwait,
            "Egypt",
            "Turkey",
            "United Arab Emirates",
            "kuwait",
            4
        ))
        questionList.add(Question(7,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_germany,
            "Greece",
            "Germany",
            "Belgium",
            "Colombia",
            2
        ))
        questionList.add(Question(8,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_india,
            "Brazil",
            "India",
            "Egypt",
            "Ireland",
            2
        ))
        questionList.add(Question(9,
            "What country does this flag belongs to?",
            R.drawable.ic_flag_of_new_zealand,
            "Australia",
            "Fiji islands",
            "New Zeeland",
            "United Kingdom",
            3
        ))

        return questionList

    }
}