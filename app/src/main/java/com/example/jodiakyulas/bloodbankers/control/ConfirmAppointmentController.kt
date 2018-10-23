package com.example.jodiakyulas.bloodbankers.control

import android.app.Activity
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.entity.Appointment

/**
 * This creates a controller class that controls the logic for the confirmation of appointment.
 * @param activity The appointment confirmation activity.
 */
class ConfirmAppointmentController(val activity: Activity) {

    /**
     * Function to populate the view.
     */
    fun populateFields() {

        val appointment = activity.intent.extras.getSerializable("Appointment") as Appointment

        val bloodBankLocationView = activity.findViewById<TextView>(R.id.bloodBankLocation)
        bloodBankLocationView.append(appointment.location)

        val bloodBankDonationTypeView = activity.findViewById<TextView>(R.id.bloodBankDonationType)
        bloodBankDonationTypeView.append(appointment.donationType)

        val bloodBankAddressView = activity.findViewById<TextView>(R.id.bloodBankAddress)
        bloodBankAddressView.append(appointment.address)

        val bloodBankPostalCodeView = activity.findViewById<TextView>(R.id.bloodBankPostalCode)
        bloodBankPostalCodeView.append(appointment.postalCode)

        val bloodBankAppointmentDateView = activity.findViewById<TextView>(R.id.bloodBankAppointmentDate)
        bloodBankAppointmentDateView.append(appointment.appointmentDate)

        val bloodBankAppointmentTimeView = activity.findViewById<TextView>(R.id.bloodBankAppointmentTime)
        bloodBankAppointmentTimeView.append(appointment.appointmentTime)

    }

}