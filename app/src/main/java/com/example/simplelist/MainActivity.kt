package com.example.simplelist

import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.ListView
import android.widget.RadioGroup
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editTextNumber = findViewById<EditText>(R.id.editTextNumber)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)
        val button = findViewById<Button>(R.id.button)
        val textView = findViewById<TextView>(R.id.textView)
        val listView = findViewById<ListView>(R.id.listView)

        button.setOnClickListener {
            val inputText = editTextNumber.text.toString()

            val n = inputText.toIntOrNull()
            if (n == null || n <= 0) {
                textView.text = "Vui lòng nhập một số nguyên dương"
                return@setOnClickListener
            }

            val selectedRadioButtonId = radioGroup.checkedRadioButtonId
            if (selectedRadioButtonId == -1) {
                textView.text = "Vui lòng chọn loại số"
                return@setOnClickListener
            }

            val result = when (selectedRadioButtonId) {
                R.id.radioButton -> getEvenNumbers(n)
                R.id.radioButton2 -> getOddNumbers(n)
                R.id.radioButton3 -> getSquareNumbers(n)
                else -> emptyList()
            }

            if (result.isEmpty()) {
                textView.text = "Không có số nào thỏa mãn điều kiện"
            } else {
                textView.text = ""

                val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, result)
                listView.adapter = adapter
            }
        }
    }

    private fun getEvenNumbers(n: Int): List<Int> {
        return (0..n).filter { it % 2 == 0 }
    }

    private fun getOddNumbers(n: Int): List<Int> {
        return (1..n).filter { it % 2 != 0 }
    }

    private fun getSquareNumbers(n: Int): List<Int> {
        val result = mutableListOf<Int>()
        var i = 0
        while (i * i <= n) {
            result.add(i * i)
            i++
        }
        return result
    }
}