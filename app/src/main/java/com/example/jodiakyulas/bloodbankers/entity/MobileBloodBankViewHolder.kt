package com.example.jodiakyulas.bloodbankers.entity

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R

class MobileBloodBankViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    var bloodBankLocation: TextView = view.findViewById(R.id.bloodBankLocation)
    var bloodBankTimings = view.findViewById<LinearLayout>(R.id.bloodDonationTimings)
    var bloodBankAddress: TextView = view.findViewById(R.id.bloodBankAddress)
    val bloodBankGoogleLocation = view.findViewById<Button>(R.id.bloodBankGoogleMap)

}