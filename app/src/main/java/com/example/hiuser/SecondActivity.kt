package com.example.hiuser

import android.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.app.DatePickerDialog
import android.content.DialogInterface
import kotlinx.android.synthetic.main.activity_main.*

class SecondActivity : AppCompatActivity() {

    companion object {
        const val TOTAL = "total"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
    }

    fun hello(view: View) {
        val name = intent.getStringExtra(TOTAL)
        val hi = AlertDialog.Builder(this)
        hi.setTitle("Приветствие")
        hi.setMessage("Привет, " + name) //Вывод сообщения с приветствием пользователя
        hi.setNeutralButton("Привет!") { dialogInterface, i ->

        }
        hi.show()
    }
}