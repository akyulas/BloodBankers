package com.example.jodiakyulas.bloodbankers.entity

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.R

class MerchantViewHolder (view: View) : RecyclerView.ViewHolder(view) {

    var merchantName: TextView = view.findViewById(R.id.merchantName)
    var merchantText: TextView = view.findViewById(R.id.merchantText)
    var merchantPoints: TextView = view.findViewById(R.id.merchantPoints)
}