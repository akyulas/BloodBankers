package com.example.jodiakyulas.bloodbankers.boundary

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.control.ChooseAppointmentTimingController
import com.example.jodiakyulas.bloodbankers.entity.Appointment
import com.example.jodiakyulas.bloodbankers.entity.BloodBank
import java.text.SimpleDateFormat
import java.util.*

/**
 * This create a boundary class that lets the user select timing details.
 */
class ChooseAppointmentTimingActivity : AppCompatActivity() {

    /**
    * Instantiate the blood bank timing controller.
    */
    val chooseAppointmentTimingController = ChooseAppointmentTimingController(this)

    /**
     * Function that gets run on creation.
     * @param savedInstanceState The bundle saves the current instance of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_choose_date_and_time)

        chooseAppointmentTimingController.prepareDate()
        chooseAppointmentTimingController.prepareTime()

    }

    /**
     * Function to load the appointment page.
     * @param v The view that the user selected.
     */
    fun loadConfirmationPage(v: View) {

        val appointmentPlace = intent.extras.getSerializable("BloodBank") as BloodBank

        val location = appointmentPlace.location
        val donationType = appointmentPlace.donationType
        val address = appointmentPlace.address
        val postalCode = appointmentPlace.postalCode


        val dateTextView = findViewById<TextView>(R.id.chosen_date)
        val appointmentDate = dateTextView.text.toString()

        val timeTextView = findViewById<TextView>(R.id.chosen_time)
        val appointmentStartingTime = timeTextView.text.toString()

        val pattern = "HH:mm"

        val date = SimpleDateFormat(pattern).parse(appointmentStartingTime)
        val calendar = Calendar.getInstance()

        calendar.time = date
        calendar.add(Calendar.HOUR, 1)

        val appointmentEndingTime = SimpleDateFormat(pattern).format(calendar.time)

        val appointmentTime  = "$appointmentStartingTime - $appointmentEndingTime"

        val sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)

        val userMatricNumber = sharedPreferences.getString("matricID", "Hacker")

        val appointment = Appointment(userMatricNumber, location, donationType, address, postalCode, appointmentDate, appointmentTime)

        val intent = Intent(this, ConfirmAppointmentActivity::class.java)
        intent.putExtra("Appointment", appointment)
        startActivity(intent)
    }

}

