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
import java.math.RoundingMode
import java.text.DecimalFormat

class MainActivity : AppCompatActivity() {

    private val TAG: String? = "MainActivity"

    //使用lateinit修饰符。 这实际是告诉编译器， 在使用属性内容时， 我们会保证提供非空 的View值
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    private lateinit var nextButton: Button
    private lateinit var prevButton: Button
    private lateinit var questionTextView: TextView
    private var userAnsweredCorrect = 0;


    //增加Question对象集合
    private val questionBank = listOf(
        Question(R.string.question_australia, answer = true, isAnswer = false),
        Question(R.string.question_oceans, answer = true, isAnswer = false),
        Question(R.string.question_mideast, answer = false, isAnswer = false),
        Question(R.string.question_africa, answer = false, isAnswer = false),
        Question(R.string.question_americas, answer = true, isAnswer = false),
        Question(R.string.question_asia, answer = true, isAnswer = false)
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
            showPercentage()
        }

        falseButton.setOnClickListener {
            checkAnswer(false)
            showPercentage()
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            updateQuestion()
            checkIfAnswered()
            showPercentage()
        }

        prevButton.setOnClickListener {
            currentIndex = (questionBank.size + currentIndex - 1) % questionBank.size
            updateQuestion()
            checkIfAnswered()
            showPercentage()
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

        var messageResID = ""
        if (userAnswer == correctAnswer) {
            messageResID = getString(R.string.correct_toast)
            userAnsweredCorrect++
        } else {
            messageResID = getString(R.string.incorrect_toast)
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

    private fun showPercentage() {
        var allAnswered = true
        for (i in questionBank.indices) {
            Log.d(TAG, "showPercentage: i=  $i")
            if (!questionBank[i].isAnswer) {
                allAnswered = false
                break
            }
        }

        if (allAnswered) {
            //百分比评分，有一个值必须转成double，否则结果不正确
            var correctMark = (userAnsweredCorrect.toDouble() / questionBank.size) * 100;
            Log.d(
                TAG,
                "showPercentage: userAnsweredCorrect= $userAnsweredCorrect   ${questionBank.size}  $correctMark"
            )
            //保留小数点后两位
            val format = DecimalFormat("#.##")
            //舍弃规则，RoundingMode.FLOOR表示直接舍弃。
            format.roundingMode = RoundingMode.FLOOR
            val text = "正确率${format.format(correctMark)}%"

            Toast.makeText(this, text, Toast.LENGTH_SHORT).show();

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