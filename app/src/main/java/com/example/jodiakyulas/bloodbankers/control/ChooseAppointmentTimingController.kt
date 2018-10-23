package com.example.jodiakyulas.bloodbankers.control

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.widget.Button
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R
import java.text.SimpleDateFormat
import java.util.*

/**
 * This creates a controller class that controls the logic for the blood bank timing activity.
 * @param activity The appointment timing activity.
 */
class ChooseAppointmentTimingController(val activity: Activity) {

    /**
     * Function to prepare the date for user.
     */
    fun prepareDate() {

        val textView  = activity.findViewById<TextView>(R.id.chosen_date)
        textView.text = SimpleDateFormat("dd.MM.yyyy").format(System.currentTimeMillis())

        val buttonView = activity.findViewById<Button>(R.id.date_picker_button)

        var cal = Calendar.getInstance()

        val dateSetListener = DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            textView.text = SimpleDateFormat("dd.MM.yyyy").format(cal.time)

        }

        buttonView.setOnClickListener() {
            DatePickerDialog(activity, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)).show()
        }
    }

    /**
     * Function to prepare the clock for user.
     */
    fun prepareTime() {

        val textView  = activity.findViewById<TextView>(R.id.chosen_time)
        textView.text = SimpleDateFormat("HH:mm").format(System.currentTimeMillis())

        val buttonView = activity.findViewById<Button>(R.id.time_picker_button)

        val cal = Calendar.getInstance()

        val timeSetListener = TimePickerDialog.OnTimeSetListener { timePicker, hour, minute ->
            cal.set(Calendar.HOUR_OF_DAY, hour)
            cal.set(Calendar.MINUTE, minute)

            textView.text = SimpleDateFormat("HH:mm").format(cal.time)
        }

        buttonView.setOnClickListener {
            TimePickerDialog(activity, timeSetListener, cal.get(Calendar.HOUR_OF_DAY), cal.get(Calendar.MINUTE), true).show()
        }
    }

}