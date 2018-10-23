package com.example.jodiakyulas.bloodbankers.control

import android.app.Activity
import android.os.Bundle
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.entity.BloodBank

/**
 * This creates a controller class that controls the logic for the blood bank details activity
 * @param activity The blood bank details activity.
 */
class BloodBankDetailsController(val activity: Activity) {

    /**
     * This populates the view with blood bank details.
     * @param bloodBankBundle The bundle contains blood bank details.
     */
    fun populateField(bloodBankBundle: Bundle) {
        val bloodBankDetails = bloodBankBundle.getSerializable("BloodBank") as BloodBank

        val bloodBankLocationView = activity.findViewById<TextView>(R.id.bloodBankLocation)
        bloodBankLocationView.append(bloodBankDetails.location)

        val bloodDonationTypeView = activity.findViewById<TextView>(R.id.bloodDonationType)
        bloodDonationTypeView.append(bloodBankDetails.donationType)

        val bloodBankAddressView = activity.findViewById<TextView>(R.id.bloodBankAddress)
        bloodBankAddressView.append(bloodBankDetails.address)

        val bloodBankPostalCodeView = activity.findViewById<TextView>(R.id.bloodBankPostalCode)
        bloodBankPostalCodeView.append(bloodBankDetails.postalCode)

        val mondayOperatingHourView = activity.findViewById<TextView>(R.id.mondayOperatingHour)
        mondayOperatingHourView.append(bloodBankDetails.mondayOperatingHour)

        val tuesdayOperatingHourView = activity.findViewById<TextView>(R.id.tuesdayOperatingHour)
        tuesdayOperatingHourView.append(bloodBankDetails.tuesdayOperatingHour)

        val wednesdayOperatingHourView = activity.findViewById<TextView>(R.id.wednesdayOperatingHour)
        wednesdayOperatingHourView.append(bloodBankDetails.wednesdayOperatingHour)

        val thursdayOperatingHourView = activity.findViewById<TextView>(R.id.thursdayOperatingHour)
        thursdayOperatingHourView.append(bloodBankDetails.thursdayOperatingHour)

        val fridayOperatingHourView = activity.findViewById<TextView>(R.id.fridayOperatingHour)
        fridayOperatingHourView.append(bloodBankDetails.fridayOperatingHour)

        val saturdayOperatingHourView = activity.findViewById<TextView>(R.id.saturdayOperatingHour)
        saturdayOperatingHourView.append(bloodBankDetails.saturdayOperatingHour)


        val sundayOperatingHourView = activity.findViewById<TextView>(R.id.sundayOperatingHour)
        sundayOperatingHourView.append(bloodBankDetails.sundayOperatingHour)

        val eveOfMajorPublicHolidayOperatingHourView = activity.findViewById<TextView>(R.id.eveOfMajorPublicHolidayOperatingHour)
        eveOfMajorPublicHolidayOperatingHourView.append(bloodBankDetails.eveOfMajorPublicHolidayOperatingHour)

        val publicHolidayOperatingHourView = activity.findViewById<TextView>(R.id.publicHolidayOperatingHour)
        publicHolidayOperatingHourView.append(bloodBankDetails.publicHolidayOperatingHour)
    }
}