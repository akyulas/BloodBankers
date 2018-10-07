package com.example.jodiakyulas.bloodbankers.adapters

import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.jodiakyulas.bloodbankers.R
import com.example.jodiakyulas.bloodbankers.classes.BloodBank
import android.widget.TextView
import com.example.jodiakyulas.bloodbankers.activities.AppointmentActivity
import com.example.jodiakyulas.bloodbankers.activities.BloodBankDetailsActivity


class BloodBankAdapter(var items: ArrayList<BloodBank>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bloodbankLocation.text = items.get(position).location
        holder.bloodDonationType.text = items.get(position).donationType
        holder.bloodBankAddress.text = items.get(position).address
        holder.bloodBankPostalCode.text = items.get(position).postalCode

        holder.itemView.setOnClickListener(object: View.OnClickListener {
            override  fun onClick(v: View?) {
                val intent = Intent(context, BloodBankDetailsActivity::class.java)
                intent.putExtra("BloodBank", items.get(position))
                context.startActivity(intent)
            }
        })
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.blood_bank_view, parent, false))
    }

    fun setBloodBanks(bloodBanks: ArrayList<BloodBank>) {
        items.addAll(bloodBanks)
        this.notifyDataSetChanged()
    }

}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    var bloodbankLocation: TextView = view.findViewById(R.id.bloodBankLocation)
    var bloodDonationType: TextView = view.findViewById(R.id.bloodDonationType)
    var bloodBankAddress: TextView = view.findViewById(R.id.bloodBankAddress)
    var bloodBankPostalCode: TextView = view.findViewById(R.id.bloodBankPostalCode)
}