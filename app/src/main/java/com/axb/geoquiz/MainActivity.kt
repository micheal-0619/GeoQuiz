package com.axb.geoquiz

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private val TAG: String? = "MainActivity"

    //使用lateinit修饰符。 这实际是告诉编译器， 在使用属性内容时， 我们会保证提供非空 的View值
    private lateinit var trueButton: Button
    private lateinit var falseButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        trueButton = findViewById(R.id.true_button)
        falseButton = findViewById(R.id.false_button)

        trueButton.setOnClickListener {
            Log.d(TAG, "onCreate: trueButton  ")
            Toast.makeText(this,R.string.correct_toast,Toast.LENGTH_LONG).show()
        }

        falseButton.setOnClickListener {
            Toast.makeText(this,R.string.incorrect_toast,Toast.LENGTH_LONG).show()
        }
    }
}