package com.axb.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.ViewModelProviders

import com.axb.geoquiz.model.QuizViewModel

private const val TAG: String = "MainActivity"
private const val KEY_INDEX = "index"

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {

    //使用lateinit修饰符。 这实际是告诉编译器， 在使用属性内容时， 我们会保证提供非空 的View值
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var questionTextView: TextView
    private var userAnsweredCorrect = 0;

    private val quizViewModel: QuizViewModel by lazy {
        ViewModelProviders.of(this).get(QuizViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Log.d(TAG, "onCreate(Bundle?) called ")

        setContentView(R.layout.activity_main)

        val currentIndex = savedInstanceState?.getInt(KEY_INDEX, 0) ?: 0
        quizViewModel.currentIndex = currentIndex

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
            quizViewModel.moveToNext()
            updateQuestion()
        }

        prevButton.setOnClickListener {
            quizViewModel.moveToPrevious()
            updateQuestion()
        }
        questionTextView.setOnClickListener {
            quizViewModel.moveToNext()
            updateQuestion()
        }

        updateQuestion()//未点击下一个之前的初始化init
    }

    private fun updateQuestion() {
        val questionTextResID = quizViewModel.currentQuestionText
        questionTextView.setText(questionTextResID)
    }

    private fun checkAnswer(userAnswer: Boolean) {
        val correctAnswer = quizViewModel.currentQuestionAnswer

        val messageResID = if (userAnswer == correctAnswer) {
            getString(R.string.correct_toast)
        } else {
            getString(R.string.incorrect_toast)
        }
        // questionBank[currentIndex].isAnswer = true

        quizViewModel.isAnswer = true
        Toast.makeText(this, messageResID, Toast.LENGTH_LONG).show()
    }

    override fun onSaveInstanceState(savedInstanceState: Bundle) {
        super.onSaveInstanceState(savedInstanceState)
        Log.i(TAG, "onSaveInstanceState")
        savedInstanceState.putInt(KEY_INDEX, quizViewModel.currentIndex)
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