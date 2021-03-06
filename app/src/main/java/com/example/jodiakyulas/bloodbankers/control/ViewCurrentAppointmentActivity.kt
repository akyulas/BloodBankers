package com.example.jodiakyulas.bloodbankers.control

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.TextView
import com.beust.klaxon.JsonArray
import com.beust.klaxon.JsonObject
import com.beust.klaxon.Parser
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.entity.Appointment
import com.example.jodiakyulas.bloodbankers.entity.OkHttpSingleton
import com.google.gson.Gson
import okhttp3.*
import java.io.IOException
import java.lang.StringBuilder
import java.text.SimpleDateFormat
import java.util.*

/**
 * This creates a controller class that controls the logic for view the current appointment.
 */
class ViewCurrentAppointmentActivity : AppCompatActivity() {

    /**
     * The current appointment.
     */
    var currentAppointment: Appointment? =null

    /**
     * Function that gets run on creation.
     * @param savedInstanceState The bundle saves the current instance of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_current_appointment)
        var appointments = ArrayList<Appointment>();
        val appointmentRecyclerView = findViewById<RecyclerView>(R.id.appointmentRecyclerView)
        val relativeLayoutManager = LinearLayoutManager(this@ViewCurrentAppointmentActivity, LinearLayoutManager.VERTICAL, false)
        appointmentRecyclerView.layoutManager = relativeLayoutManager
        appointmentRecyclerView.addItemDecoration(DividerItemDecoration(this@ViewCurrentAppointmentActivity, DividerItemDecoration.VERTICAL))
        val appointmentAdapter = AppointmentAdapter(appointments, this@ViewCurrentAppointmentActivity)
        appointmentRecyclerView.adapter = appointmentAdapter
        populateActivity(appointmentAdapter)
    }

    /**
     * Function to populate the view.
     * @param appointmentAdapter The adapter that will be used to modify the view.
     */
    fun populateActivity(appointmentAdapter: AppointmentAdapter) {

        val sharedPreferences = getSharedPreferences("SharedPreferences", Context.MODE_PRIVATE)
        val userMatricNumber = sharedPreferences.getString("matricID", "Hacker")

        val client = OkHttpSingleton.getClient()

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

                        val appointment = getCurrentAppointment(appointmentString)

                        if (appointment == null) {

                            val currentAppointmentTextView = findViewById<TextView>(R.id.current_appointment)
                            currentAppointmentTextView.text = "You have no appointments currently. Please make more appointments."

                        } else {
                            val currentAppointmentTextView = findViewById<TextView>(R.id.current_appointment)
                            currentAppointmentTextView.text = "This is your current appointment."
                            val appointments = ArrayList<Appointment>()
                            appointments.add(appointment)
                            currentAppointment = appointment
                            appointmentAdapter.setAppointments(appointments)
                            val viewLocationButton = findViewById<Button>(R.id.viewLocationButton)
                            viewLocationButton.visibility = View.VISIBLE

                            val deleteAppointmentButton = findViewById<Button>(R.id.deleteAppointmentButton)
                            deleteAppointmentButton.visibility = View.VISIBLE

                        }

                    }

                }

            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

        })
    }

    /**
     * Function to open the address in google map.
     */
    fun openInGoogleMap(v: View) {
        val postalCodeView = findViewById<TextView>(R.id.bloodBankPostalCode)
        val mapURL = "http://maps.google.com.sg/maps?q=${postalCodeView.text}"
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(mapURL))
        startActivity(intent)
    }

    /**
     * Function to show show warning before deleting.
     */
    fun showWarningBeforeDeleting(v: View) {

        val builder = AlertDialog.Builder(this)
        builder.setTitle("Warning")
        builder.setMessage("Your appointment will be cancelled.")

        builder.setPositiveButton(android.R.string.ok) {
            dialog, which ->
            deleteAppointment()
        }

        builder.setNegativeButton(android.R.string.cancel) {
            dialog, which ->
        }

        val dialog: AlertDialog = builder.create()
        dialog.show()

    }

    /**
     * Function to delete appointment.
     */
    fun deleteAppointment() {
        val json = Gson().toJson(currentAppointment)

        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        val client = OkHttpSingleton.getClient()
        val url = "http://10.0.2.2:8090/appointment"
        val request = Request.Builder().url(url).delete(body).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {

                val responseString = response.body()?.string()

                runOnUiThread {
                    val builder = AlertDialog.Builder(this@ViewCurrentAppointmentActivity)
                    if (responseString.equals("An error occured. Please try again.")) {
                        builder.setTitle("Error")
                        builder.setMessage("Something went wrong. Please try again.")
                        builder.setPositiveButton(android.R.string.ok) {
                            dialog, which ->
                        }
                    } else {
                        builder.setTitle("Success")
                        builder.setMessage("The appointment has been deleted.")
                        builder.setPositiveButton(android.R.string.ok) {
                            dialog, which ->
                            val intent = Intent(this@ViewCurrentAppointmentActivity, MainActivity::class.java)
                            startActivity(intent)
                        }
                    }
                    val dialog: AlertDialog = builder.create()
                    dialog.show()
                }
            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }
        })
    }

    /**
     * Get the current appointment.
     * @param appointmentString The json appointment string returned by the backend server.
     * @return The current appointment.
     */
    fun getCurrentAppointment(appointmentString: String?) : Appointment?{
        if (appointmentString == null || appointmentString == "[]") {
            return null
        }
        val parser: Parser = Parser()
        var json: JsonArray<JsonObject> = parser.parse(StringBuilder(appointmentString)) as JsonArray<JsonObject>
        val jsonObj = json.get(0)

        val appointmentDate = jsonObj.string("appointmentDate").toString()

        val appointmentTime = jsonObj.string("appointmentTime").toString()

        val timings = appointmentTime.split(" - ".toRegex())
        val endTime = timings.get(1)

        val appointmentEndDate = "${appointmentDate} ${endTime}"

        val pattern = "dd.MM.yyyy HH:mm"
        val endingDate = SimpleDateFormat(pattern).parse(appointmentEndDate)

        if (endingDate.after(Calendar.getInstance().time)) {
            val userMatricID = jsonObj.string("userMatricID").toString()
            val location = jsonObj.string("location").toString()
            val donationType = jsonObj.string("donationType").toString()
            val address = jsonObj.string("address").toString()
            val postalCode = jsonObj.string("postalCode").toString()
            return Appointment(userMatricID, location, donationType, address, postalCode, appointmentDate, appointmentTime)
        } else {
            return null
        }

    }

}