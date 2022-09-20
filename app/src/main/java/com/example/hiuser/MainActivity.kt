package com.example.hiuser

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Registration.isEnabled = false
    }
    fun calendar(view: View) { //Создание календаря
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val birhtday = DatePickerDialog(this, DatePickerDialog.OnDateSetListener{view, y, m, d ->
            DateText.setText("$d.${m+1}.$y")
        }, year, month, day)
        birhtday.datePicker.maxDate = System.currentTimeMillis()
        birhtday.show()
    }
    fun check(word: String, worderr: String, errors: TextView) : Int { //Метод проверки на разрешенные символы символы
        errors.visibility = View.INVISIBLE
        var k = 0
        for (char in word) {
            if (((char !in 'a'..'z') and (char !in 'A'..'Z') and (char !in 'а'..'я') and (char !in 'А'..'Я') and (worderr != "p")) || ((char !in 'a'..'z') and (char !in 'A'..'Z') and (char !in '0'..'9') and (worderr == "p"))) {
                k = 1
                errors.visibility = View.VISIBLE
                break
            }
        }
        return k
    }
    fun registr(view: View) {
        var name = NameText.text.toString() //Имя пользователя
        var surname = SurNameText.text.toString() //Фамилия пользователя
        var date = DateText.text.toString() //День рождения пользователя
        var password = PasswordText.text.toString()  //Пароль
        var passwordre = PasswordReText.text.toString() //Повтор пароля
        var k=0
        var err=0;
        if (name.isNullOrEmpty()) { //Проверка введенного имени на ошибки
            NameErr.visibility = View.VISIBLE
            err++
        }
        else {
            k=check(name, "o", NameErr)
            if (k==1) err++
        }
        if (surname.isNullOrEmpty()) { //Проверка введенной фамилии на ошибки
            SurNameErr.visibility = View.VISIBLE
            err++
        }
        else {
            k=check(surname, "o", SurNameErr)
            if (k==1) err++
        }
        if (date.isNullOrEmpty()) {  //Проверка введенния даты рождения
            DateErr.visibility = View.VISIBLE
            err++
        }
        else
            DateErr.visibility=View.INVISIBLE
        if (password.length < 8) {  //Проверка введенного пароля
            PasswordErr.visibility = View.VISIBLE
            err++
        }
        else {
            k=check(password, "p", PasswordErr)
            if (k==1) err++
        }
        if (passwordre.isNullOrEmpty()) { //Проверка повторения пароля
            PasswordReErr.visibility = View.VISIBLE
            err++
        }
        else {
            PasswordReErr.visibility = View.INVISIBLE
            if (password != passwordre) {
                PasswordReErr.visibility=View.VISIBLE
                err++
            }
            else {
                PasswordReErr.visibility=View.INVISIBLE
            }
        }
        if (err==0) { //Передача данных на второй экран
            val HiName = Intent (this, SecondActivity::class.java)
            HiName.putExtra(SecondActivity.TOTAL, name)
            startActivity(HiName)
        }
    }
}