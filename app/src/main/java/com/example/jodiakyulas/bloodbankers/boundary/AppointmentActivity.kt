package com.example.jodiakyulas.bloodbankers.boundary

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DividerItemDecoration
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.control.AppointmentController
import com.example.jodiakyulas.bloodbankers.control.BloodBankAdapter
import com.example.jodiakyulas.bloodbankers.entity.BloodBank
import okhttp3.*
import java.io.IOException

/**
 * This creates a boundary class that is related to the appointment.
 */
class AppointmentActivity : AppCompatActivity() {

    /**
     * Instantiate the appointment controller.
     */
    val appointmentController = AppointmentController()

    /**
     * Function that gets run on creation.
     * @param savedInstanceState The bundle saves the current instance of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_make_appointment)
        val bloodBankRecyclerView = findViewById<RecyclerView>(R.id.bloodBankRecyclerView)
        val relativeLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        bloodBankRecyclerView.layoutManager = relativeLayoutManager
        bloodBankRecyclerView.addItemDecoration(DividerItemDecoration(this, DividerItemDecoration.VERTICAL))
        val bloodBankAdapter = BloodBankAdapter(ArrayList<BloodBank>(), this)
        bloodBankRecyclerView.adapter = bloodBankAdapter
        populateAPIBloodBankAdapter(bloodBankAdapter)

    }

    /**
     * Function to populate the blood bank adapter.
     * @param bloodBankAdapter The blood bank adapter that will be populated.
     */
    fun populateAPIBloodBankAdapter(bloodBankAdapter: BloodBankAdapter) {
        val client = OkHttpClient()
        val getBloodBankURL = "http://10.0.2.2:8090/queryAPI"
        val request = Request.Builder().url(getBloodBankURL).build()
        client.newCall(request).enqueue(object: Callback {
            override fun onResponse(call: Call, response: Response) {
                val bloodBanksJsonString = response.body()?.string()
                val bloodBanks = appointmentController.createBloodBankDataset(bloodBanksJsonString)
                runOnUiThread {
                    bloodBankAdapter.setBloodBanks(bloodBanks)
                }

            }

            override fun onFailure(call: Call, e: IOException) {
                e.printStackTrace()
            }

        })

    }

}