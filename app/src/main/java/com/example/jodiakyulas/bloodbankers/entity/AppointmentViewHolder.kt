package com.example.jodiakyulas.bloodbankers.entity

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R

/**
 * This is the view holder class for the appointment.
 * @param view The view which will be searched for information that the view holder needs.
 * @property bloodbankLocation The blood bank location for the appointment.
 * @property bloodDonationType The blood bank donation type.
 * @property bloodBankAddress The blood bank address.
 * @property bloodBankPostalCode The blood bank postal code.
 * @property appointmentDate The blood bank appointment date.
 * @property appointmentTime The blood bank appointment time.
 */
class AppointmentViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    var bloodbankLocation: TextView = view.findViewById(R.id.bloodBankLocation)
    var bloodDonationType: TextView = view.findViewById(R.id.bloodBankDonationType)
    var bloodBankAddress: TextView = view.findViewById(R.id.bloodBankAddress)
    var bloodBankPostalCode: TextView = view.findViewById(R.id.bloodBankPostalCode)
    var appointmentDate: TextView = view.findViewById(R.id.bloodBankAppointmentDate)
    var appointmentTime: TextView = view.findViewById(R.id.bloodBankAppointmentTime)
}