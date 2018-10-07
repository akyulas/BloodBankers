package com.example.jodiakyulas.bloodbankers.activities

import android.app.DatePickerDialog
import android.app.FragmentTransaction
import android.app.TimePickerDialog
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R
import java.text.SimpleDateFormat
import java.util.*

class ChooseAppointmentTimingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_date_and_time)

        prepareDate()
        prepareTime()


    }

    fun prepareDate() {

        val textView  = findViewById<TextView>(R.id.chosen_date)
        textView.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())

        val buttonView = findViewById<Button>(R.id.date_picker_button)

        var cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            textView.text = SimpleDateFormat("dd.MM.yyyy").format(cal.time)

        }

        buttonView.setOnClickListener() {
                DatePickerDialog(this, dateSetListener,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH)).show()
            }
        }

    fun prepareTime() {

        val textView  = findViewById<TextView>(R.id.chosen_time)
        textView.text = SimpleDateFormat("HH:mm").format(System.currentTimeMillis())

        val buttonView = findViewById<Button>(R.id.time_picker_button)

        val cal = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)

            textView.text = SimpleDateFormat("HH:mm").format(cal.time)
        }

        buttonView.setOnClickListener {
            TimePickerDialog(this, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
    }

}

