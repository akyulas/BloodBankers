package com.example.jodiakyulas.bloodbankers.boundary

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.control.BloodBankDetailsController
import com.example.jodiakyulas.bloodbankers.entity.BloodBank

/**
 * This create a boundary class that shows the blood bank details.
 */
class BloodBankDetailsActivity : AppCompatActivity() {

    /**
     * Instantiate the blood bank details controller.
     */
    val bloodBankDetailsController = BloodBankDetailsController(this)

    /**
     * Function that gets run on creation.
     * @param savedInstanceState The bundle saves the current instance of the activity.
     */
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blood_bank_details)
        val bloodBankBundle = intent.extras
        bloodBankDetailsController.populateField(bloodBankBundle)
    }

    /**
     * Function to load the appointment page.
     * @param v The view that the user selected.
     */
    fun loadAppointmentTimePage(v: View) {
        val bloodBank = intent.extras.getSerializable("BloodBank") as BloodBank
        val intent = Intent(this, ChooseAppointmentTimingActivity::class.java)
        intent.putExtra("BloodBank" , bloodBank)
        startActivity(intent)
    }


}