package com.axb.geoquiz.model

import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.axb.geoquiz.MainActivity
import com.axb.geoquiz.R
import com.axb.geoquiz.data.Question
import java.math.RoundingMode
import java.text.DecimalFormat

private const val TAG = "QuizViewModel"

class QuizViewModel: ViewModel() {
    var currentIndex = 0
    private var userAnsweredCorrect = 0;
    private lateinit var text: String


    //增加Question对象集合
    private val questionBank = listOf(
        Question(R.string.question_australia, answer = true, isAnswer = false),
        Question(R.string.question_oceans, answer = true, isAnswer = false),
        Question(R.string.question_mideast, answer = false, isAnswer = false),
        Question(R.string.question_africa, answer = false, isAnswer = false),
        Question(R.string.question_americas, answer = true, isAnswer = false),
        Question(R.string.question_asia, answer = true, isAnswer = false)
    )


    val currentQuestionAnswer: Boolean
        get() = questionBank[currentIndex].answer

    val currentQuestionText: Int
        get() = questionBank[currentIndex].textResId
    var isAnswer: Boolean
        get() = questionBank[currentIndex].isAnswer
        set(value) {}

    fun moveToNext() {
        currentIndex = (currentIndex + 1) % questionBank.size
    }

    fun moveToPrevious() {
        currentIndex = (questionBank.size + currentIndex - 1) % questionBank.size
    }

}

