package com.example.jodiakyulas.bloodbankers.boundary

import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.widget.TextView
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.control.AppointmentAdapter
import com.example.jodiakyulas.bloodbankers.control.ViewAppointmentHistoryController
import com.example.jodiakyulas.bloodbankers.entity.Appointment
import okhttp3.*
import java.io.IOException
import java.lang.StringBuilder
import java.util.*

/**
 * This create a boundary class that lets the user view the appointment history.
 */
class ViewAppointmentHistoryActivity : AppCompatActivity() {

    /**
     * Instantiate the view appointment history controller.
     */
    val viewAppointmentHistoryController = ViewAppointmentHistoryController()

    /**
     * Function that gets run on creation.
     * @param savedInstanceState The bundle saves the current instance of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appointment_history)
        var appointments = ArrayList<Appointment>();
        val appointmentRecyclerView = findViewById<RecyclerView>(R.id.appointmentRecyclerView)
        val relativeLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        appointmentRecyclerView.layoutManager = relativeLayoutManager
        appointmentRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val appointmentAdapter = AppointmentAdapter(appointments, this)
        appointmentRecyclerView.adapter = appointmentAdapter
        populateActivity(appointmentAdapter)
    }

    /**
     * Function to populate the appointment adapter.
     * @param bloodBankAdapter The appointment adapter that will be populated.
     */
    fun populateActivity(appointmentAdapter: AppointmentAdapter) {

        val sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
        val userMatricNumber = sharedPreferences.getString("matricID", "Hacker")

        val client = OkHttpClient()

        val appointmentURL = "http://10.0.2.2:8090/appointment?m=$userMatricNumber"

//        val url = HttpUrl.Builder().url("http://10.0.2.2:8090/appointment").addQueryParameter("m", userMatricNumber).build()
        val request = Request.Builder().url(appointmentURL).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {

                val appointmentString = response.body()?.string()

                runOnUiThread {

                    if (appointmentString.equals("An error occured. Please try again.")) {

                        val currentAppointmentTextView = findViewById<TextView>(R.id.current_appointment)
                        currentAppointmentTextView.text = appointmentString

                    } else {

                        val appointments = viewAppointmentHistoryController.getAppointmentHistory(appointmentString)

                        if (appointments == null) {

                            val currentAppointmentTextView = findViewById<TextView>(R.id.current_appointment)
                            currentAppointmentTextView.text = "You have no appointments currently. Please make more appointments."

                        } else {
                            val currentAppointmentTextView = findViewById<TextView>(R.id.current_appointment)
                            currentAppointmentTextView.text = "This is your appointment history."
                            appointmentAdapter.setAppointments(appointments)

                        }

                    }

                }

            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

        })
    }



}