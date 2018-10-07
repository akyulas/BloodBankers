package com.example.jodiakyulas.bloodbankers.activities

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.classes.BloodBank

class BloodBankDetailsActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.blood_bank_details)
        val bloodBankBundle = intent.extras
        populateField(bloodBankBundle)
    }

    fun loadAppointmentTimePage(v: View) {
        val bloodBank = intent.extras.getSerializable("BloodBank") as BloodBank
        val intent = Intent(this, ChooseAppointmentTimingActivity::class.java)
        intent.putExtra("BloodBank" , bloodBank)
        startActivity(intent)
    }

    fun populateField(bloodBankBundle: Bundle) {
        val bloodBankDetails = bloodBankBundle.getSerializable("BloodBank") as BloodBank

        val bloodBankLocationView = findViewById<TextView>(R.id.bloodBankLocation)
        bloodBankLocationView.append(bloodBankDetails.location)

        val bloodDonationTypeView = findViewById<TextView>(R.id.bloodDonationType)
        bloodDonationTypeView.append(bloodBankDetails.donationType)

        val bloodBankAddressView = findViewById<TextView>(R.id.bloodBankAddress)
        bloodBankAddressView.append(bloodBankDetails.address)

        val bloodBankPostalCodeView = findViewById<TextView>(R.id.bloodBankPostalCode)
        bloodBankPostalCodeView.append(bloodBankDetails.postalCode)

        val mondayOperatingHourView = findViewById<TextView>(R.id.mondayOperatingHour)
        mondayOperatingHourView.append(bloodBankDetails.mondayOperatingHour)

        val tuesdayOperatingHourView = findViewById<TextView>(R.id.tuesdayOperatingHour)
        tuesdayOperatingHourView.append(bloodBankDetails.tuesdayOperatingHour)

        val wednesdayOperatingHourView = findViewById<TextView>(R.id.wednesdayOperatingHour)
        wednesdayOperatingHourView.append(bloodBankDetails.wednesdayOperatingHour)

        val thursdayOperatingHourView = findViewById<TextView>(R.id.thursdayOperatingHour)
        thursdayOperatingHourView.append(bloodBankDetails.thursdayOperatingHour)

        val fridayOperatingHourView = findViewById<TextView>(R.id.fridayOperatingHour)
        fridayOperatingHourView.append(bloodBankDetails.fridayOperatingHour)

        val saturdayOperatingHourView = findViewById<TextView>(R.id.saturdayOperatingHour)
        saturdayOperatingHourView.append(bloodBankDetails.saturdayOperatingHour)


        val sundayOperatingHourView = findViewById<TextView>(R.id.sundayOperatingHour)
        sundayOperatingHourView.append(bloodBankDetails.sundayOperatingHour)

        val eveOfMajorPublicHolidayOperatingHourView = findViewById<TextView>(R.id.eveOfMajorPublicHolidayOperatingHour)
        eveOfMajorPublicHolidayOperatingHourView.append(bloodBankDetails.eveOfMajorPublicHolidayOperatingHour)

        val publicHolidayOperatingHourView = findViewById<TextView>(R.id.publicHolidayOperatingHour)
        publicHolidayOperatingHourView.append(bloodBankDetails.publicHolidayOperatingHour)

    }
}