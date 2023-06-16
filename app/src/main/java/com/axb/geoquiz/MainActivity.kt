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
    private lateinit var questionTextView: TextView

    //增加Question对象集合
    private val questionBank = listOf(
        Question(R.string.question_australia, true),
        Question(R.string.question_oceans, true),
        Question(R.string.question_mideast, false),
        Question(R.string.question_africa, false),
        Question(R.string.question_americas, true),
        Question(R.string.question_asia, true)
    )
    private var currentIndex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        nextButton = findViewById(R.id.next_button)
        questionTextView = findViewById(R.id.question_text_view)

        trueButton.setOnClickListener {
            Log.d(TAG, "onCreate: trueButton  ")
            val toast = Toast.makeText(this, R.string.correct_toast, Toast.LENGTH_LONG)
            toast.setGravity(Gravity.TOP, 0, 0)//顶部显示
            toast.show()

        }

        falseButton.setOnClickListener {
            Toast.makeText(this, R.string.incorrect_toast, Toast.LENGTH_LONG).show()
        }

        nextButton.setOnClickListener {
            currentIndex = (currentIndex + 1) % questionBank.size
            val questionTextResID = questionBank[currentIndex].textResId
            questionTextView.setText(questionTextResID)
        }

        val questionTextResID = questionBank[currentIndex].textResId
        questionTextView.setText(questionTextResID)
    }
}