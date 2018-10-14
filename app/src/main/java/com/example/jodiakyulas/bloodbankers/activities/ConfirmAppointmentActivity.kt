package com.example.jodiakyulas.bloodbankers.activities

import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.classes.Appointment
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_confirm_timing.*
import okhttp3.*
import org.w3c.dom.Text
import java.io.IOException

class ConfirmAppointmentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_confirm_timing)
        populateFields()
    }

    fun populateFields() {

        val appointment = intent.extras.getSerializable("Appointment") as Appointment

        val bloodBankLocationView = findViewById<TextView>(R.id.bloodBankLocation)
        bloodBankLocationView.append(appointment.location)

        val bloodBankDonationTypeView = findViewById<TextView>(R.id.bloodBankDonationType)
        bloodBankDonationTypeView.append(appointment.donationType)

        val bloodBankAddressView = findViewById<TextView>(R.id.bloodBankAddress)
        bloodBankAddressView.append(appointment.address)

        val bloodBankPostalCodeView = findViewById<TextView>(R.id.bloodBankPostalCode)
        bloodBankPostalCodeView.append(appointment.postalCode)

        val bloodBankAppointmentDateView = findViewById<TextView>(R.id.bloodBankAppointmentDate)
        bloodBankAppointmentDateView.append(appointment.appointmentDate)

        val bloodBankAppointmentTimeView = findViewById<TextView>(R.id.bloodBankAppointmentTime)
        bloodBankAppointmentTimeView.append(appointment.appointmentTime)

    }

    fun confirmAppointment(v: View) {

        val appointment = intent.extras.getSerializable("Appointment") as Appointment
        val json = Gson().toJson(appointment)

        val body = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), json)
        val client = OkHttpClient()
        val url = "http://10.0.2.2:8090/appointment"
        val request = Request.Builder().url(url).post(body).build()

        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {

                runOnUiThread {
                    val responseMessage = response.body()?.string()
                    val builder = AlertDialog.Builder(this@ConfirmAppointmentActivity)
                    if (responseMessage.equals("The appointment is successful.")) {
                        builder.setTitle("Success")
                        builder.setMessage(responseMessage)
                    } else {
                        builder.setTitle("Error")
                        builder.setMessage(responseMessage)
                    }
                    builder.setPositiveButton(android.R.string.ok) {
                        dialog, which ->
                        val intent = Intent(this@ConfirmAppointmentActivity, MainActivity::class.java)
                        startActivity(intent)
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

}