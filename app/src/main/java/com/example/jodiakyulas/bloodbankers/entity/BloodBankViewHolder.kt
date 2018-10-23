package com.example.jodiakyulas.bloodbankers.entity

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R

/**
 * This is the view holder class for the blood banks.
 * @param view The view which will be searched for information that the view holder needs.
 * @property bloodbankLocation The blood bank location for the appointment.
 * @property bloodDonationType The blood bank donation type.
 * @property bloodBankAddress The blood bank address.
 * @property bloodBankPostalCode The blood bank postal code.
*/

class BloodBankViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    var bloodbankLocation: TextView = view.findViewById(R.id.bloodBankLocation)
    var bloodDonationType: TextView = view.findViewById(R.id.bloodDonationType)
    var bloodBankAddress: TextView = view.findViewById(R.id.bloodBankAddress)
    var bloodBankPostalCode: TextView = view.findViewById(R.id.bloodBankPostalCode)
}