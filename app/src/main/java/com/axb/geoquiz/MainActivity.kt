package com.axb.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.widget.AutoCompleteTextView
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.axb.geoquiz.data.Question

class MainActivity : AppCompatActivity() {

    private val TAG: String? = "MainActivity"

    //使用lateinit修饰符。 这实际是告诉编译器， 在使用属性内容时， 我们会保证提供非空 的View值
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var questionTextView: TextView

    //增加Question对象集合
    private val questionBank = listOf(
        Question(R.string.question_australia, true, false),
        Question(R.string.question_oceans, true, false),
        Question(R.string.question_mideast, false, false),
        Question(R.string.question_africa, false, false),
        Question(R.string.question_americas, true, false),
        Question(R.string.question_asia, true, false)
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate(Bundle?) called ")

        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        nextButton = findViewById(R.id.next_button)
        prevButton = findViewById(R.id.previous_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener {
            checkAnswer(true)
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
            checkIfAnswered()
        }

        prevButton.setOnClickListener {
            currentIndex = (questionBank.size + currentIndex - 1) % questionBank.size
            updateQuestion()
            checkIfAnswered()
        }
        questionTextView.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
        }

        updateQuestion()//未点击下一个之前的初始化init
    }

    private fun updateQuestion() {
        val questionTextResID = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResID)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = questionBank[currentIndex].answer

        val messageResID = if (userAnswer == correctAnswer) {
            R.string.correct_toast
        } else {
            R.string.incorrect_toast
        }
        questionBank[currentIndex].isAnswer = true
        checkIfAnswered()
        Toast.makeText(this, messageResID, Toast.LENGTH_LONG).show()
    }

    private fun checkIfAnswered() {

        val isAnswered = questionBank[currentIndex].isAnswer;
        if (isAnswered) {
            trueButton.isEnabled = false
            falseButton.isEnabled = false
        } else {
            trueButton.isEnabled = true
            falseButton.isEnabled = true
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart: called")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume: called ")
    }

    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause: called ")
    }

    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop: called ")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy: called ")
    }
}